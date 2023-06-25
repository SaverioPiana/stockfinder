package siw.stockfinder.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import siw.stockfinder.Util.Api.DeserializedPriceData;
import siw.stockfinder.model.PriceData;

import java.util.List;
@Service
public class ApiService {
    @Value("${alphavantage.apiKey}")
    private String apiKey;
    private final String timeSeriesIntraday = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=";
    public List<PriceData> fetchPriceData(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        String url = timeSeriesIntraday + symbol + "&interval=5min&apikey=" + apiKey;
        DeserializedPriceData response = restTemplate.getForObject(url, DeserializedPriceData.class);
        return response.toPriceDataList();
    }

}


