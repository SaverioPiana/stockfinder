package siw.stockfinder.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import siw.stockfinder.Util.Api.DeserializedPriceData;
import siw.stockfinder.model.PriceData;

import java.time.LocalDateTime;
import java.util.SortedMap;

@Service
public class ApiService {
    @Value("${alphavantage.apiKey}")
    private String stockApiKey;
    private final String timeSeriesIntraday = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=";
    public SortedMap<LocalDateTime, PriceData> fetchPriceData(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        String url = timeSeriesIntraday + symbol + "&interval=5min&apikey=" + stockApiKey;
        DeserializedPriceData response = restTemplate.getForObject(url, DeserializedPriceData.class);
        return response.toPriceDataSortedMap();
    }

}


