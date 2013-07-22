package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: terence
 * Date: 20/07/13
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class User extends Model {
    @Id
    private Long id;
    private String username, password, cookie;

    public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
