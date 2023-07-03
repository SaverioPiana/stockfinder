package siw.stockfinder.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import siw.stockfinder.Util.OrderDTO;
import siw.stockfinder.model.Order;
import siw.stockfinder.model.PriceData;
import siw.stockfinder.model.Stock;
import siw.stockfinder.model.User;
import siw.stockfinder.service.OrderService;
import siw.stockfinder.service.StockService;
import siw.stockfinder.service.UserService;
import siw.stockfinder.validator.OrderValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    StockService stockService;
    @Autowired
    UserService userService;

    //for the value chart
    //accessible to everyone
    @GetMapping("/user/{id}/orders/json")
    public ResponseEntity<List<OrderDTO>> getUserOrders(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        if (user != null) {
            List<Order> orderList = new ArrayList<>(user.getOrders());
            return ResponseEntity.ok(orderService.toDTO(orderList));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/registered/order/buy/{stockId}")
    public String buyStock(@PathVariable("stockId") Long stockId, @Valid @ModelAttribute("order") Order order, BindingResult bindingResult, Model model) {
        Stock stock = stockService.findById(stockId);
        if(stock == null) {
            model.addAttribute("error", "Stock not found");
            return "errors/genericError";
        }
        OrderValidator orderValidator = new OrderValidator();
        orderValidator.validate(order, bindingResult);
        if(!bindingResult.hasErrors()) {
            orderService.buyStock(order, stock, userService.getCurrentUser());
        }
        return "redirect:/stock/" + stockId;
    }
    @PostMapping("/registered/order/sell/{stockId}")
    public String sellStock(@PathVariable("stockId") Long stockId, @Valid @ModelAttribute("order") Order order, BindingResult bindingResult, Model model) {
        Stock stock = stockService.findById(stockId);
        if(stock == null) {
            model.addAttribute("error", "Stock not found");
            return "errors/genericError";
        }
        //to add validation (if user stock quantity is less than the order quantity)
        if(!bindingResult.hasErrors()) {
            orderService.sellStock(order, stock, userService.getCurrentUser());
        }
        return "redirect:/stock/" + stockId;
    }
}
