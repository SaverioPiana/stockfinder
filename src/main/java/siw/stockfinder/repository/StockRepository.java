package siw.stockfinder.repository;

import org.springframework.data.repository.CrudRepository;
import siw.stockfinder.model.Stock;

public interface StockRepository extends CrudRepository<Stock, Long> {
    public boolean existsBySymbol(String symbol);
    public Stock findBySymbol(String symbol);
}
