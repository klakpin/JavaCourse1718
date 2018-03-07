package model.pojo;

import java.time.LocalDateTime;

public class Training {
    private int id;
    private int userId;
    private LocalDateTime dateTime;
    private int rating;


    public Training(int id, int userId, LocalDateTime dateTime, int rating) {
        this.id = id;
        this.userId = userId;
        this.dateTime = dateTime;
        this.rating = rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
