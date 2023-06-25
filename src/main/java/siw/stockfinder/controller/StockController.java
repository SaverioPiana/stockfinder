package siw.stockfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import siw.stockfinder.model.Stock;
import siw.stockfinder.service.ApiService;
import siw.stockfinder.service.StockService;

@Controller
public class StockController {
    @Autowired
    StockService stockService;
    @Autowired
    ApiService apiService;


    @GetMapping("/stock")
    public String showAllStocks(Model model){
        model.addAttribute(stockService.findAll());
        return "stocks";
    }
    @GetMapping("/stock/{id}")
    public String showStock(@PathVariable("id") Long id, Model model){
        model.addAttribute(stockService.findById(id));
        return "stock";
    }
    //for url search
    @GetMapping("/stock/symbol/{symbol}")
    public String showStock(@PathVariable("symbol") String symbol, Model model){
        model.addAttribute(stockService.findBySymbol(symbol));
        return "stock";
    }
}
