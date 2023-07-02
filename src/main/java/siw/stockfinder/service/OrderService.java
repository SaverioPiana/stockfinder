package siw.stockfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siw.stockfinder.model.Order;
import siw.stockfinder.model.Stock;
import siw.stockfinder.model.User;
import siw.stockfinder.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void buyStock(Order order,Stock stock, User user) {
        order.setType("buy");
        order.setStock(stock);
        order.setUser(user);
        orderRepository.save(order);
    }
    public void sellStock(Order order,Stock stock, User user) {
        order.setType("sell");
        order.setStock(stock);
        order.setUser(user);
        orderRepository.save(order);
    }
}
