package siw.stockfinder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import siw.stockfinder.Util.Order.OrderType;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private OrderType type;
    @OneToOne
    private Stock stock;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    private float quantity;
    private float price;

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

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
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
