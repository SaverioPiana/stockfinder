package siw.stockfinder.Util.Api;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public class TimeSeriesEntry {
        @JsonProperty("1. open")
        private String open;

        @JsonProperty("2. high")
        private String high;

        @JsonProperty("3. low")
        private String low;

        @JsonProperty("4. close")
        private String close;

        @JsonProperty("5. volume")
        private String volume;

        public String getOpen() {
            return open;
        }

        public void setOpen(String open) {
            this.open = open;
        }

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getLow() {
            return low;
        }

        public void setLow(String low) {
            this.low = low;
        }

        public String getClose() {
            return close;
        }

        public void setClose(String close) {
            this.close = close;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }
    }
}

