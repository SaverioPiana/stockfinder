package siw.stockfinder.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import siw.stockfinder.Util.Api.DeserializedPriceData;
import siw.stockfinder.model.PriceData;

import java.util.List;
@Service
public class ApiService {
    private final String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=demo";

    public List<PriceData> fetchPriceData(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        DeserializedPriceData response = restTemplate.getForObject(apiUrl, DeserializedPriceData.class);
        return response.toPriceDataList();
    }
}


