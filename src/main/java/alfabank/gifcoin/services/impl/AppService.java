package alfabank.gifcoin.services.impl;

import alfabank.gifcoin.services.api.GifClient;
import alfabank.gifcoin.services.api.RateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AppService {

    private Random random = new Random();
    private double oldRate;
    private double newRate;

    @Value("${currency.name}")
    private String currency;

    @Autowired
    GifClient gifClient;

    @Autowired
    RateClient rateClient;

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(fixedRate = 24, timeUnit = TimeUnit.HOURS)
    private void getRates() {
        String yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_DATE);
        this.oldRate = Double.parseDouble(rateClient.getYesterdayRate(yesterday).getRates().get(currency));
        this.newRate = Double.parseDouble(rateClient.getTodayRate().getRates().get(currency));
        System.out.println(oldRate);
    }

    public String getGif() {
        int index = random.nextInt(20);
        var gif = this.oldRate > this.newRate
                ? gifClient.getGifsBroke()
                : gifClient.getGifsRich();

        return gif.getData().get(index).getImages().getOriginal().getUrl();
    }


}
