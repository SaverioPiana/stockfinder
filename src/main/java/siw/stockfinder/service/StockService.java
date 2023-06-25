package siw.stockfinder.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siw.stockfinder.model.Stock;
import siw.stockfinder.repository.StockRepository;

@Service
public class StockService {
    @Autowired
    StockRepository stockRepository;

    @Transactional
    public void save(Stock stock){
        stockRepository.save(stock);
    }
    @Transactional
    public void delete(Stock stock){
        stockRepository.delete(stock);
    }
    @Transactional
    public boolean existsBySymbol(String symbol){
        return stockRepository.existsBySymbol(symbol);
    }
    public Iterable<Stock> findAll(){
        return stockRepository.findAll();
    }
    public Stock findById(Long id){
        return stockRepository.findById(id).orElse(null);
    }
    public Stock findBySymbol(String symbol){
        return stockRepository.findBySymbol(symbol);
    }
}
