package siw.stockfinder.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import siw.stockfinder.model.Order;
import siw.stockfinder.model.Stock;
import siw.stockfinder.repository.StockRepository;

@Component
public class OrderValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Stock.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Order order= (Order) o;

        if(order.getType()!="buy" && order.getType()!="sell") {
            errors.reject("orderError","order.wrongType");
        }
        if(order.getType()=="buy" && (order.getPrice()>order.getUser().getCurrentFunds())) {
            errors.reject("orderError","order.NotEnoughFunds");
        }
        if(order.getType()=="sell" && (order.getQuantity()>
                order.getUser().getQuantityPerStockSet(order.getStock().getSymbol()).getQuantity())) {
            errors.reject("orderError","order.NotEnoughQuantity");
        }
    }
}
