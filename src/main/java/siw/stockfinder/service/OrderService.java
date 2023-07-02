package siw.stockfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siw.stockfinder.Util.Order.OrderType;
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

    public void buyStock(Stock stock, User user) {
        Order order = new Order();
        order.setType(OrderType.BUY);
        order.setStock(stock);
        order.setUser(user);
        orderRepository.save(order);
    }
    public void sellStock(Stock stock, User user) {
        Order order = new Order();
        order.setType(OrderType.SELL);
        order.setStock(stock);
        order.setUser(user);
        orderRepository.save(order);
    }
}
