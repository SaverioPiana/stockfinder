package siw.stockfinder.service;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siw.stockfinder.model.PriceData;
import siw.stockfinder.model.Stock;
import siw.stockfinder.repository.StockRepository;

import java.util.List;

@Service
public class StockService {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    ApiService apiService;

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
    @Transactional
    public void updateAllStocks(){
        Iterable<Stock> stocks = stockRepository.findAll();
        for(Stock stock : stocks){
            //to avoid lazy initialization exception
            Hibernate.initialize(stock.getPriceHistory());
            List<PriceData> priceDataList = apiService.fetchPriceData(stock.getSymbol());
            boolean updated = false;
            for (PriceData priceData : priceDataList) {
                if(priceData.getTimeStamp().isAfter(stock.getLastUpdated())){
                    stock.getPriceHistory().add(priceData);
                    updated = true;
                }
                else break;
            }
            if(updated){
                stock.setLastUpdated(priceDataList.get(0).getTimeStamp());
                stockRepository.save(stock);
            }
        }
    }
@Transactional
    public void addNewStock(Stock stock) {
        List<PriceData> priceDataList = apiService.fetchPriceData(stock.getSymbol());
        stock.setPriceHistory(priceDataList);
        stock.setLastUpdated(priceDataList.get(0).getTimeStamp());
        stockRepository.save(stock);
    }
}
