package ar.com.wolox.android.training.model;
import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String picture;
    private String cover;
    private String description;
    private String location;
    private String name;
    private String phone;

    public User() {}

    public User(Integer id, String username, String email, String password, String picture, String cover, String description, String location, String name, String phone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.cover = cover;
        this.description = description;
        this.location = location;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPicture() {
        return picture;
    }

    public String getCover() {
        return cover;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
