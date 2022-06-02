package alfabank.gifcoin.services.impl;

import alfabank.gifcoin.models.gif.DataImages;
import alfabank.gifcoin.models.gif.ImagesOriginal;
import alfabank.gifcoin.models.gif.OriginalUrl;
import alfabank.gifcoin.models.gif.ResponseGif;
import alfabank.gifcoin.models.rate.ResponseRate;
import alfabank.gifcoin.services.api.GifClient;
import alfabank.gifcoin.services.api.RateClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppServiceTest {

    @Mock
    GifClient gifClient;

    @Mock
    RateClient rateClient;

    @Mock
    Random random;

    private AppService appService;

    private ResponseGif responseGifRich;
    private DataImages dataImagesRich;
    private ImagesOriginal imagesOriginalRich;
    private OriginalUrl originalUrlRich;
    private String urlRich;

    private ResponseRate responseRateYesterday;
    private ResponseRate responseRateToday;
    private String currency;
    private String rateYesterday;
    private String rateToday;


    @BeforeEach
    void setUp() {
        currency = "EUR";
        rateYesterday = "0.93564";
        rateToday = "0.94564";
        urlRich = "https://rich.gif";

        appService = new AppService(gifClient, rateClient, random);

        responseRateYesterday = new ResponseRate(new HashMap<>(Map.of(currency, rateYesterday)));
        responseRateToday = new ResponseRate(new HashMap<>(Map.of(currency,rateToday)));

        originalUrlRich = new OriginalUrl(urlRich);
        imagesOriginalRich = new ImagesOriginal(originalUrlRich);
        dataImagesRich = new DataImages(imagesOriginalRich);
        responseGifRich = new ResponseGif(new ArrayList<>(List.of(dataImagesRich)));
    }

    @Test
    void shouldGetGif() {

        when(random.nextInt(anyInt())).thenReturn(0);

        when(rateClient.getYesterdayRate(anyString(), anyString()))
                .thenReturn(responseRateYesterday);

        when(rateClient.getTodayRate(anyString()))
                .thenReturn(responseRateToday);

        when(gifClient.getGifsRich(anyString(), anyString()))
                .thenReturn(responseGifRich);

        appService.getRates();

        assertEquals(appService.getGif(), urlRich);

    }
}