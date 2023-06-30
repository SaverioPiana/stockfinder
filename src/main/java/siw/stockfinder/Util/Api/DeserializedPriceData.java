package siw.stockfinder.Util.Api;

import com.fasterxml.jackson.annotation.JsonProperty;
import siw.stockfinder.model.PriceData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class DeserializedPriceData {
    @JsonProperty("3. Last Refreshed")
    private String lastRefreshed;
    @JsonProperty("Time Series (5min)")
    private Map<String, TimeSeriesEntry> timeSeries;

    public Map<String, TimeSeriesEntry> getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(Map<String, TimeSeriesEntry> timeSeries) {
        this.timeSeries = timeSeries;
    }
    public SortedMap<LocalDateTime, PriceData> toPriceDataSortedMap() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        SortedMap<LocalDateTime, PriceData> priceDataSortedMap = new TreeMap<>();
        for (Map.Entry<String, TimeSeriesEntry> entry:
                timeSeries.entrySet()) {
            PriceData priceData = new PriceData();
            priceData.setOpen(entry.getValue().getOpen());
            priceData.setClose(entry.getValue().getClose());
            priceData.setHigh(entry.getValue().getHigh());
            priceData.setLow(entry.getValue().getLow());
            priceData.setTimeStamp(LocalDateTime.parse(entry.getKey(), formatter));
            priceDataSortedMap.put(priceData.getTimeStamp(), priceData);
        }
        return priceDataSortedMap;
    }
}

