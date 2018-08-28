package ar.com.wolox.android.training.model;

public class NewsLikeEvent {
    private Integer idUser;
    private Integer idNews;
    private Boolean isLiked;

    public NewsLikeEvent(Integer idUser, Integer idNews, Boolean isLiked) {
        this.idUser = idUser;
        this.idNews = idNews;
        this.isLiked = isLiked;
    }

    public Integer getIdNews() {
        return idNews;
    }

    public void applyTo(News news) {
        if (isLiked) {
            news.userLike(idUser);
        } else {
            news.userDislike(idUser);
        }
    }
}
