package siw.stockfinder.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import siw.stockfinder.model.Credentials;
import siw.stockfinder.model.PriceData;
import siw.stockfinder.model.Stock;
import siw.stockfinder.model.User;
import siw.stockfinder.service.ApiService;
import siw.stockfinder.service.CredentialsService;
import siw.stockfinder.service.StockService;
import siw.stockfinder.validator.StockValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class StockController {
    @Autowired
    StockService stockService;
    @Autowired
    ApiService apiService;
    @Autowired
    StockValidator stockValidator;
    @Autowired
    private CredentialsService credentialsService;


    @GetMapping("/stocks")
    public String showAllStocks(Model model){
        model.addAttribute("stocks",stockService.findAll());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
        if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "admin/stocks";
        }
        return "stocks";
    }
    //for the chart
    @GetMapping("/stock/{id}/json")
    public ResponseEntity<List<PriceData>> getStock(@PathVariable("id") Long id) {
        Stock stock = stockService.findById(id);
        if (stock != null) {
            Collection<PriceData> values = stock.getPriceHistory().values();
            List<PriceData> priceDataList = new ArrayList<>(values);
            return ResponseEntity.ok(priceDataList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/stock/{id}")
    public String showStock(@PathVariable("id") Long id, Model model){
        model.addAttribute("stock",stockService.findById(id));
        return "stock";
    }
    //for url search
    @GetMapping("/stock/symbol/{symbol}")
    public String showStock(@PathVariable("symbol") String symbol, Model model){
        model.addAttribute("stock",stockService.findBySymbol(symbol));
        return "stock";
    }

    /*############  ADMIN  ###############*/

    @GetMapping("/admin/stock")
    public String showAllStocksAdmin(Model model){
        model.addAttribute("stocks",stockService.findAll());
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
        model.addAttribute("stock" ,new Stock());
        return "admin/formNewStock";
    }

    @PostMapping("/admin/stock")
    public String saveStock(@Valid @ModelAttribute("stock") Stock stock, BindingResult bindingResult, Model model){
        this.stockValidator.validate(stock, bindingResult);
        if(!bindingResult.hasErrors()){
            this.stockService.addNewStock(stock);
            model.addAttribute("stock", stock);
            return showStockAdmin(stock.getId(), model);

        }else{
            model.addAttribute("messaggioErrore", "Questa stock è gia nel sistema, inseriscine una nuova)");
            return "redirect:/admin/formNewStock";
        }

    }

    //DA AGGIUSTARE PER AVERE USER CORRENTE
    @GetMapping("/profile")
    public String showProfile(@ModelAttribute("user") User user, Model model){
        model.addAttribute("user", user);
        return "registered/profile";
    }
}
