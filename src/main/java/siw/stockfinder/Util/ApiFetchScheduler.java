package siw.stockfinder.Util;

import siw.stockfinder.model.PriceData;

public class ApiFetchScheduler {
    private final String apiUrl="https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=demo";
    private ApiFetchScheduler() {}
    private static final ApiFetchScheduler INSTANCE = new ApiFetchScheduler();
    public static ApiFetchScheduler getInstance(){ return INSTANCE; }

    public PriceData fetch(String symbol){
        return
    }
}
