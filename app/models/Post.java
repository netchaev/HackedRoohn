package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: terence
 * Date: 20/07/13
 * Time: 22:48
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Post extends Model {
    @Id
    private String id;
    private String title;
    private String url;
    private Category category;
    private User creator;
    private Date createDate;

    public static Finder<String, Post> find = new Finder<String, Post>(String.class, Post.class);

    public static enum Category {
        HASKELL, CSHARP, JAVA, PHP, JAVASCRIPT,
        ANDROID, JQUERY, CPP, PYHTON, HTML,
        ASPNET, MYSQL, DOTNET, CSS, OBJECTIVE_C,
        RUBY, C, RUBY_ON_RAILS, AJAX, JSON
    }

    public Post(String title, String url, User creator, Category category){
        createDate = new Date();
        this.creator = creator;
        this.title = title;
        this.url = url;
        this.category = category;
        save();
    }

    public Post(String title, String url, Category category){
        this.id = title+url;
        createDate = new Date();
        this.title = title;
        this.url = url;
        this.category = category;
        save();
    }

    public static boolean alreadyHere(String title, String url){
        return (Post.find.byId(title+url)!=null);
    }

    public List<Post> getPostsByCategory(Category category){
        LinkedList<Post> posts = new LinkedList<Post>();
        for(Post p : find.all()){
            if(p.category.equals(category))
                posts.add(p);
        }
        return posts;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Category getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public boolean equals(Object other){
        return ((Post)other).getId().equals(getId());
    }
}