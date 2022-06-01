package alfabank.gifcoin.services.api;

import alfabank.gifcoin.models.rate.ResponseRate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "rateClient", url = "${rate.api}")
public interface RateClient {

    @GetMapping("historical/{yesterday}.json?app_id=fab23c349494445881fd48dbc464149b")
    ResponseRate getYesterdayRate(@PathVariable String yesterday);

    @GetMapping("latest.json?app_id=fab23c349494445881fd48dbc464149b")
    ResponseRate getTodayRate();


}
