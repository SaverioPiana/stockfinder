package siw.stockfinder.Util.Api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeSeriesEntry {
    @JsonProperty("1. open")
    private float open;

    @JsonProperty("2. high")
    private float high;

    @JsonProperty("3. low")
    private float low;

    @JsonProperty("4. close")
    private float close;

    @JsonProperty("5. volume")
    private int volume;

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
