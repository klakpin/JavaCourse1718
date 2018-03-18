package ilya.project.spring.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trainings")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "trainingdatetime", columnDefinition = "TIMESTAMP WITHOUT TIMEZONE")
    private LocalDateTime dateTime;

    @Column(name = "rating")
    private Integer rating;

    public Training() {
    }

    public Training(Long userId, LocalDateTime dateTime, int rating) {
        this.userId = userId;
        this.dateTime = dateTime;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
