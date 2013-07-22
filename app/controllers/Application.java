package controllers;

import models.Post;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import play.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(main.render("submit"));
    }

    public static Result search(String str){
        List<Post> posts = Post.find.all();
        List<Post> found = new LinkedList<Post>();
        StringBuilder builder = new StringBuilder();
        for(String s : str.split("&")){
            for(Post p : posts)
                if(p.getCategory().toString().equalsIgnoreCase(s))
                    found.add(p);
        }
        return ok(results.render(found));
    }

    public static Result categories() {
        return ok(main.render("categories"));
    }

    public static Result update(){
        StringBuilder builder = new StringBuilder();

        try{
        URL url = new URL("http://api.ihackernews.com/page");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        String line = null;
        while((line = reader.readLine()) != null)
            builder.append(line);
        } catch (Exception ignore){ignore.printStackTrace();}
        if(builder.length() < 2)
            return ok("Nothing to update.");
        JsonNode node = Json.parse(builder.toString());
        StringBuilder printtest = new StringBuilder();
        for(JsonNode t : node.get("items")){
            for(String split : t.get("title").getTextValue().split(" ")){
                    for(Post.Category category : Post.Category.values()){
                        if(category.toString().equalsIgnoreCase(split)){
                            if(!Post.alreadyHere(t.get("title").getTextValue(), t.get("url").getTextValue())) {
                            Post p = new Post(t.get("title").getTextValue(), t.get("url").getTextValue(), category);
                            p.save();                                                                         }
                            printtest.append(t.get("title").getTextValue());
                        }
                    }
            }
        }
        return ok(printtest.toString()).as("html");
    }

    public static Result posts(){
        List<Post> posts = Post.find.all();
        StringBuilder sb = new StringBuilder();
        sb.append("No# Posts:"+posts.size());
        for(Post p : posts)
            sb.append(p.getTitle()).append(", ");
        return ok(sb.toString());
    }


    public static Result submit() {

        final Map<String, String[]> values = request().body().asFormUrlEncoded();
        ObjectNode node = Json.newObject();
        final String title = values.get("title")[0];
        final String url = values.get("url")[0];
        final String category = values.get("category")[0];

        Post.Category cate = null;
        for(Post.Category cat : Post.Category.values()){
            if(cat.toString().equalsIgnoreCase(category.toString()))
                cate = cat;
        }
        models.Post post = new models.Post(title, url, cate);
        node.put("reply", "worked");
        return ok(node);
    }
  
}
