package siw.stockfinder.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import siw.stockfinder.model.Stock;
import siw.stockfinder.repository.StockRepository;

@Component
public class StockValidator implements Validator {
    @Autowired
    StockRepository stockRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Stock.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Stock stock= (Stock) o;
        if(stock.getSymbol()!=null && stockRepository.existsBySymbol(stock.getSymbol()) ) {
            errors.reject("stock.duplicate");
        }
    }
}
