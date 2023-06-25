package siw.stockfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import siw.stockfinder.Util.Api.DeserializedPriceData;
import siw.stockfinder.model.PriceData;
import siw.stockfinder.model.Stock;
import siw.stockfinder.service.ApiService;
import siw.stockfinder.service.StockService;

import java.util.List;

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

    /*############  ADMIN  ###############*/

    @GetMapping("/admin/stock")
    public String showAllStocksAdmin(Model model){
        model.addAttribute(stockService.findAll());
        return "admin/stocks";
    }
    @GetMapping("/admin/stock/{id}")
    public String showStockAdmin(@PathVariable("id") Long id, Model model){
        model.addAttribute(stockService.findById(id));
        return "admin/stock";
    }
    //for url search
    @GetMapping("/admin/stock/symbol/{symbol}")
    public String showStockAdmin(@PathVariable("symbol") String symbol, Model model){
        model.addAttribute(stockService.findBySymbol(symbol));
        return "admin/stock";
    }
    @GetMapping("/admin/formNewStock")
    public String showFormNewStock(Model model){
        model.addAttribute(new Stock());
        return "admin/formNewStock";
    }
    //to do: add validation
    @PostMapping("/admin/stock")
    public String saveStock(Stock stock){
        if(!stockService.existsBySymbol(stock.getSymbol())){
           stockService.addNewStock(stock);
        }
        return "redirect:/admin/stock";
    }
}
