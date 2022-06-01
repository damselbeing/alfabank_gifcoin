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

    @Value("${app.id}")
    private String appId;

    @Value("${api.key}")
    private String apiKey;

    @Value("${q.rich}")
    private String rich;

    @Value("${q.broke}")
    private String broke;

    @Autowired
    GifClient gifClient;

    @Autowired
    RateClient rateClient;

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(fixedRate = 24, timeUnit = TimeUnit.HOURS)
    private void getRates() {
        String yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_DATE);
        oldRate = Double.parseDouble(
                rateClient.getYesterdayRate(yesterday, appId)
                        .getRates()
                        .get(currency));
        newRate = Double.parseDouble(
                rateClient.getTodayRate(appId)
                        .getRates()
                        .get(currency));
    }

    public String getGif() {
        int index = random.nextInt(50);
        var gif = oldRate > newRate
                ? gifClient.getGifsBroke(broke, apiKey)
                : gifClient.getGifsRich(rich, apiKey);
        return gif.getData().get(index).getImages().getOriginal().getUrl();
    }


}
