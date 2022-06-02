package alfabank.gifcoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Random;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class GifcoinApplication {

    @Bean
    public Random getRandom() {
        return new Random();
    }

    public static void main(String[] args) {
        SpringApplication.run(GifcoinApplication.class, args);
    }

}
