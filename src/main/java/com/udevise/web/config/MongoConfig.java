package com.udevise.web.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

  @Value("${spring.data.mongodb.host}")
  private String host;

  @Value("${spring.data.mongodb.database}")
  private String databaseName;

  @Value("${spring.data.mongodb.port}")
  private String port;

    public @Bean
    MongoClient mongoClient() {
      return new MongoClient(host);
    }

    public @Bean
    MongoTemplate mongoTemplate() {
      return new MongoTemplate(mongoClient(), databaseName);
    }
}
