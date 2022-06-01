package alfabank.gifcoin.services.api;

import alfabank.gifcoin.models.rate.ResponseRate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "rateClient", url = "${rate.api}")
public interface RateClient {

    @GetMapping("historical/{yesterday}.json")
    ResponseRate getYesterdayRate(
            @PathVariable String yesterday,
            @RequestParam String app_id);

    @GetMapping("latest.json")
    ResponseRate getTodayRate(@RequestParam String app_id);


}
