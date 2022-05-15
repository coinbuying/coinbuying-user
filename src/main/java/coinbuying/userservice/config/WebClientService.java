package coinbuying.userservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientService {

    @Value("${msa.client.url.gateway}")
    private String gatewayUrl;

    public WebClientService(WebClient.Builder builder) {
        WebClient client = builder.baseUrl(gatewayUrl).build();
    }

}
