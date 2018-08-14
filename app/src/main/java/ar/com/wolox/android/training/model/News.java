package ar.com.wolox.android.training.model;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;

import java.util.ArrayList;
import java.util.List;

public class News {
    private Integer id;
    private Integer userId;
    private DateTime createdAt;
    private String title;
    private String picture;
    private String text;
    private List<Integer> likes;

    public News() {
        this.likes = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getPicture() { return picture; }

    public String getText() {
        return text;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public List<Integer> getLikes() {
        return likes;
    }

    public String getSince() {
        return (getSinceHours() > 0 ? String.valueOf(getSinceHours()) + "h " : "") +
                (getSinceHours() == 0 || getSinceMinutes() > 0 ? String.valueOf(getSinceMinutes() % 60) + "m" : "");
    }

    public Integer getSinceHours() {
        DateTime now = new DateTime();
        return Hours.hoursBetween(getCreatedAt(), now).getHours();
    }

    public Integer getSinceMinutes() {
        DateTime now = new DateTime();
        return Minutes.minutesBetween(getCreatedAt(), now).getMinutes();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setLikes(List<Integer> likes) {
        this.likes = likes;
    }

    public Boolean isLikedByUser(Integer userId) {
        return getLikes().contains(userId);
    }
}