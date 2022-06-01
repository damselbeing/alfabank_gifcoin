package alfabank.gifcoin.models.rate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRate {

    private HashMap<String, String> rates;

}