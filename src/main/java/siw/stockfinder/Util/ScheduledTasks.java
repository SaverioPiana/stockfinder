package siw.stockfinder.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import siw.stockfinder.service.StockService;

@Component
public class ScheduledTasks {
    @Autowired
    StockService stockService;
    //every 10 minutes
    @Scheduled(cron = "0 */10 * * * *")//sec min hour day month dayOfWeek
    public void updateAllStocks(){
        stockService.updateAllStocks();
    }
}
