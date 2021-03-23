
package sample.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SampleWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(SampleWebApplication.class, args);
  }

}
