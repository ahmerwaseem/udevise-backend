package com.udevise.web.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.URI;

@Configuration
public class MongoConfig {

  @Value("${spring.data.mongodb.uri}")
  private MongoClientURI uri;

  @Value("${spring.data.mongodb.database}")
  private String databaseName;

    public @Bean
    MongoClient mongoClient() {
      return new MongoClient(uri);
    }

    public @Bean
    MongoTemplate mongoTemplate() {
      return new MongoTemplate(mongoClient(), databaseName);
    }
}
