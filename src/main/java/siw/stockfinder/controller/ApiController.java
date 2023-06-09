package siw.stockfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import siw.stockfinder.service.StockService;

@RestController
public class ApiController {

    @Value("${alphavantage.apiKey}")
    private String stockApiKey;
    @GetMapping("/api/searchSymbol/{text}")
    public String getDataFromApi(@PathVariable("text") String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        // Make API request using the apiKey
        String apiUrl = "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords="+ symbol + "&apikey=" + stockApiKey;
        return restTemplate.getForObject(apiUrl, String.class);
    }
}
