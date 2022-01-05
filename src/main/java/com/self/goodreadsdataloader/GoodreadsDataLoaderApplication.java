package com.self.goodreadsdataloader;

import com.self.goodreadsdataloader.connection.DataStaxAstraProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class GoodreadsDataLoaderApplication {

  public static void main(String[] args) {
    SpringApplication.run(GoodreadsDataLoaderApplication.class, args);
  }

  @Bean
  public CqlSessionBuilderCustomizer sessionBuilderCustomizer(
      DataStaxAstraProperties dataStaxAstraProperties) {
    Path bundle = dataStaxAstraProperties.getSecureConnectBundle().toPath();
    return cqlSessionBuilder -> cqlSessionBuilder.withCloudSecureConnectBundle(bundle);
  }
}
