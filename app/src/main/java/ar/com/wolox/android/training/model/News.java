package ar.com.wolox.android.training.model;

import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class News implements Serializable {
    private Integer id;
    private Integer userId;
    private LocalDateTime createdAt;
    private String title;
    private String picture;
    private String text;
    private List<Integer> likes;

    public News() {
        this.likes = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPicture() { return picture; }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Integer> getLikes() {
        return likes;
    }

    public String getSince() {
        if (getCreatedAt() == null) {
            return null;
        }

        return (getSinceHours() > 0 ? String.valueOf(getSinceHours()) + "h " : "") +
                (getSinceHours() == 0 || getSinceMinutes() % 60 > 0 ? String.valueOf(getSinceMinutes() % 60) + "m" : "");
    }

    public Integer getSinceHours() {
        LocalDate now = new LocalDate();
        return Hours.hoursBetween(getCreatedAt(), now).getHours();
    }

    public Integer getSinceMinutes() {
        LocalDate now = new LocalDate();
        return Minutes.minutesBetween(getCreatedAt(), now).getMinutes();
    }

    public Boolean isLikedByUser(Integer userId) {
        return getLikes().contains(userId);
    }

    public void userDislike(Integer userDislikeId) {
        likes.remove(userDislikeId);
    }

    public void userLike(Integer userLikeId) {
        likes.add(userLikeId);
    }
}