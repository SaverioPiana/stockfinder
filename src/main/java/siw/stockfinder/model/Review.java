package siw.stockfinder.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String title;
    private LocalDateTime creationDateTime;
    private String content;
    @ManyToOne
    private Stock reviewedStock;
    @ManyToOne
    private User author;

    public Review(){

    }
    public Review(Stock movie, User author){
        this.reviewedStock = movie;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(title, review.title) && Objects.equals(creationDateTime, review.creationDateTime) && Objects.equals(author, review.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, creationDateTime, author);
    }

    public Stock getReviewedStock() {
        return reviewedStock;
    }

    public void setReviewedStock(Stock reviewedMovie) {
        this.reviewedStock = reviewedMovie;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }
    public String getCreationDateTimeFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" - dd-MM-yyyy - HH:mm");
        return this.creationDateTime.format(formatter);
    }

    public void setCreationDateTime(LocalDateTime date) {
        this.creationDateTime = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
