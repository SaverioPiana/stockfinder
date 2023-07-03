package siw.stockfinder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String email;
    private String picFilename;
    @OneToMany(mappedBy = "user")
    private Set<Order> orders;
    private float totalGeneratedFunds;
    private float currentFunds;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Stock> starredStocks;
    @OneToMany(mappedBy = "author")
    private Set<Review> reviews;

    public Set<Order> getOrdersFromStock(Stock stock) {
        Set<Order> orders = this.getOrders();
        orders.removeIf(order -> !order.getStock().equals(stock));
        return orders;
    }
    public float getTotalSpent(){
        float totalSpent = 0;
        for(Order order: orders){
            if(order.getType().equals("buy")){
                totalSpent += order.getPrice();
            }
        }
        return totalSpent;
    }
    public float getTotalProfit(){
        float totalProfit = 0;
        for(Order order: orders){
            if(order.getType().equals("buy")){
                totalProfit -= order.getPrice();
            }
            else{
                totalProfit += order.getPrice();
            }
        }
        return totalProfit;
    }
    public Map<String,Float> getQuantityPerStock() {
        Map<String,Float> quantityPerStock = new HashMap<>();
        for(Order order: orders){
            if(order.getType().equals("buy")){
                if(quantityPerStock.containsKey(order.getStock().getSymbol())){
                    quantityPerStock.put(order.getStock().getSymbol(),
                            quantityPerStock.get(order.getStock().getSymbol()) + order.getQuantity());
                }
                else{
                    quantityPerStock.put(order.getStock().getSymbol(), order.getQuantity());
                }
            }
            else{
                if(quantityPerStock.containsKey(order.getStock().getSymbol())){
                    quantityPerStock.put(order.getStock().getSymbol(),
                            quantityPerStock.get(order.getStock().getSymbol()) - order.getQuantity());
                }
                else{
                    quantityPerStock.put(order.getStock().getSymbol(), -order.getQuantity());
                }
            }
        }
        return quantityPerStock;
    }
    public float getTotalGeneratedFunds() {
        return totalGeneratedFunds;
    }

    public void setTotalGeneratedFunds(float totalGeneratedFunds) {
        this.totalGeneratedFunds = totalGeneratedFunds;
    }

    public float getCurrentFunds() {
        return currentFunds;
    }
    public void removeFunds(float funds){
        this.currentFunds -= funds;
    }
    public void addFunds(float funds){
        this.currentFunds += funds;
    }

    public void setCurrentFunds(float currentFunds) {
        this.currentFunds = currentFunds;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email);
    }
    public String getPicPath(){
        if(picFilename != null) return "/upload/images/user_pics/" + this.getId() + "/"
                +this.getPicFilename();
        return "/images/default_profile_pic.png";
    }

    public Set<Stock> getStarredStocks() {
        return starredStocks;
    }

    public void setStarredStocks(Set<Stock> starredStocks) {
        this.starredStocks = starredStocks;
    }

    public Set<Review> getReviews() {
        return reviews;
    }
    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public String getPicFilename() {
        return picFilename;
    }
    public void setPicFilename(String path_of_pic) {
        this.picFilename = path_of_pic;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}