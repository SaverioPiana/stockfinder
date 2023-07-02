package siw.stockfinder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import siw.stockfinder.service.ApiService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String symbol;
    @OneToMany(cascade = CascadeType.ALL)
    @MapKey(name = "timeStamp")
    private SortedMap<LocalDateTime, PriceData> priceHistory;
    @Column(columnDefinition = "TEXT")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriceData getLastPrice(){
        PriceData lastPrice = new PriceData();
        if(priceHistory.size()>0){
            lastPrice = priceHistory.get(priceHistory.lastKey());
        }
        return lastPrice;
    }
    public float getDaysVariation(int numberOfDays){
        float variation = 0;
        if(priceHistory.size()>0){
            PriceData lastPrice = priceHistory.get(priceHistory.lastKey());
            LocalDateTime firstDate = priceHistory.lastKey().minus(numberOfDays, java.time.temporal.ChronoUnit.DAYS);
            PriceData firstPrice = priceHistory.get(firstDate);
            //if firstPrice is null, try -10 minutes
            if(firstPrice==null){
                firstPrice = priceHistory.get(firstDate.minus(10, java.time.temporal.ChronoUnit.MINUTES));
            }
            //if it is still null take the first price
            if(firstPrice==null){
                firstPrice = priceHistory.get(priceHistory.firstKey());
            }
            variation = (lastPrice.getClose()-firstPrice.getOpen())/firstPrice.getOpen()*100;
        }
        return variation;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(symbol, stock.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public SortedMap<LocalDateTime, PriceData> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(SortedMap<LocalDateTime, PriceData> priceHistory) {
        this.priceHistory = priceHistory;
    }
}
