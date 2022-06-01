package alfabank.gifcoin.services.api;

import alfabank.gifcoin.models.gif.ResponseGif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "gifClient", url = "${gif.api}")
public interface GifClient {

    @GetMapping("search?q=rich&api_key=eRZ3y0A7pGYCSu3atmRUsoFIPG0k7Bke")
    ResponseGif getGifsRich();

    @GetMapping("search?q=broke&api_key=eRZ3y0A7pGYCSu3atmRUsoFIPG0k7Bke")
    ResponseGif getGifsBroke();

}