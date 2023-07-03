package siw.stockfinder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    @ManyToOne(cascade = CascadeType.ALL)
    private Stock stock;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @Min(0)
    private float quantity;
    @Min(0)
    private float price;

    private LocalDateTime timeStamp;

    public String getReadableTimeStamp(){
        return timeStamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss"));
    }
    public float getVariation(){
        float variation = 0;
        PriceData lastPrice = stock.getLastPrice();
        float firstPrice = price/quantity;
        return (lastPrice.getClose()-firstPrice)/firstPrice*100;
    }
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime dateTime) {
        this.timeStamp = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
