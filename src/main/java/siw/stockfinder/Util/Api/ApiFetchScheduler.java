package siw.stockfinder.Util.Api;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.client.RestTemplate;

public class ApiFetchScheduler {
    private final String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=demo";

    private ApiFetchScheduler() {
    }

    private static final ApiFetchScheduler INSTANCE = new ApiFetchScheduler();

    public static ApiFetchScheduler getInstance() {
        return INSTANCE;
    }
    public Object fetchData(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        DeserializedPriceData data = restTemplate.getForObject(apiUrl, DeserializedPriceData.class);
        return data;
    }
}


