package siw.stockfinder.controller;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import siw.stockfinder.model.Order;
import siw.stockfinder.model.Stock;
import siw.stockfinder.service.OrderService;
import siw.stockfinder.service.StockService;
import siw.stockfinder.service.UserService;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    StockService stockService;
    @Autowired
    UserService userService;

    @PostMapping("/registered/order/buy/{stockId}")
    public String buyStock(@PathVariable("stockId") Long stockId, @Valid @ModelAttribute("order") Order order, BindingResult bindingResult) {
        Stock stock = stockService.findById(stockId);
        if(stock == null) {
            return "redirect:/stock";
        }
        //to add validation (if user balance is less than the order price*quantity)
        if(!bindingResult.hasErrors()) {
            orderService.buyStock(order, stock, userService.getCurrentUser());
        }
        return "redirect:/stock/" + stockId;
    }
    @PostMapping("/registered/order/sell/{stockId}")
    public String sellStock(@PathVariable("stockId") Long stockId, @Valid @ModelAttribute("order") Order order, BindingResult bindingResult) {
        Stock stock = stockService.findById(stockId);
        if(stock == null) {
            return "redirect:/stock";
        }
        //to add validation (if user stock quantity is less than the order quantity)
        if(!bindingResult.hasErrors()) {
            orderService.sellStock(order, stock, userService.getCurrentUser());
        }
        return "redirect:/stock/" + stockId;
    }
}
