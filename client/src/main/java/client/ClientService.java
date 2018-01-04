package client;

import data.RequestDTO;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

@SpringBootApplication
@RestController
public class ClientService {
  private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);

  @Autowired
  private RestTemplate restTemplate;

  public static void main(String[] args) {
    SpringApplication.run(ClientService.class, args);
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder
//        .setConnectTimeout(500)
//        .setReadTimeout(1000)
        .build();
  }

  @PostMapping
  public DeferredResult<String> submit(@RequestBody RequestDTO data) {
    LOG.info("data: {}", data);
    DeferredResult<String> deferredResult = new DeferredResult<>();
    CompletableFuture.runAsync(() -> {
          String result = restTemplate.postForObject("http://localhost:8001/", data, String.class);
          if (!deferredResult.hasResult()) {
            deferredResult.setResult(result);
          } else {
            LOG.info("Adding result '{}' to queue", result);
          }
        }
    ).exceptionally(e -> {
      if (!deferredResult.hasResult()) {
        deferredResult.setResult("Processing");
      }
      return null;
    });

    deferredResult.onTimeout(() ->
        deferredResult.setResult("Processing")
    );
    return deferredResult;
  }

}
