package alfabank.gifcoin.services.api;

import alfabank.gifcoin.models.gif.ResponseGif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "gifClient", url = "${gif.api}")
public interface GifClient {

    @GetMapping()
    ResponseGif getGifsRich(@RequestParam (name = "q") String rich,
                            @RequestParam String api_key);

    @GetMapping()
    ResponseGif getGifsBroke(@RequestParam (name = "q") String broke,
                             @RequestParam String api_key);

}