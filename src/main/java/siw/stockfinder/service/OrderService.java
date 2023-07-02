package siw.stockfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siw.stockfinder.Util.OrderDTO;
import siw.stockfinder.model.Order;
import siw.stockfinder.model.Stock;
import siw.stockfinder.model.User;
import siw.stockfinder.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        order.setTimeStamp(LocalDateTime.now());
        user.removeFunds(order.getPrice());
        orderRepository.save(order);
    }
    public void sellStock(Order order,Stock stock, User user) {
        order.setType("sell");
        order.setStock(stock);
        order.setUser(user);
        order.setTimeStamp(LocalDateTime.now());
        user.addFunds(order.getPrice());
        orderRepository.save(order);
    }
    public List<OrderDTO> toDTO(List<Order> orders) {
        List<OrderDTO> orderDTOS = new java.util.ArrayList<>(orders.stream().map(this::toDTO).toList());
        // Sort the list based on timestamp
        orderDTOS.sort(new Comparator<OrderDTO>() {
            @Override
            public int compare(OrderDTO o1, OrderDTO o2) {
                return o1.getTimeStamp().compareTo(o2.getTimeStamp());
            }
        });
        return orderDTOS;
    }
    public OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setType(order.getType());
        orderDTO.setStockId(order.getStock().getId());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setPrice(order.getPrice());
        orderDTO.setTimeStamp(order.getTimeStamp());
        return orderDTO;
    }
}
