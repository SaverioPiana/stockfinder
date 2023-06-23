package siw.stockfinder.Util.Api;

import com.fasterxml.jackson.annotation.JsonProperty;
import siw.stockfinder.model.PriceData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeserializedPriceData {
    @JsonProperty("Time Series (5min)")
    private Map<String, TimeSeriesEntry> timeSeries;


    public Map<String, TimeSeriesEntry> getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(Map<String, TimeSeriesEntry> timeSeries) {
        this.timeSeries = timeSeries;
    }
    public List<PriceData> toPriceDataList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<PriceData> priceDataList = new ArrayList<>();
        for (Map.Entry<String, TimeSeriesEntry> entry:
                timeSeries.entrySet()) {
            PriceData priceData = new PriceData();
            priceData.setOpen(entry.getValue().getOpen());
            priceData.setClose(entry.getValue().getClose());
            priceData.setTimeStamp(LocalDateTime.parse(entry.getKey(), formatter));
            priceDataList.add(priceData);
        }
        return priceDataList;
    }
}

