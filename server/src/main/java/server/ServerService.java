package server;

import data.RequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServerService {
  private static final Logger LOG = LoggerFactory.getLogger(ServerService.class);

  public static void main(String[] args) {
    SpringApplication.run(ServerService.class, args);
  }

  @PostMapping
  public String submit(@RequestBody RequestDTO data) throws Exception {
    LOG.info("data: {}", data);
    Thread.sleep(data.getDelay());
    return "Done";
  }
}
