package com.github.dbmdz.flusswerk.example.stubs;

import com.github.dbmdz.flusswerk.example.model.IndexDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IndexClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(IndexClient.class);

  public void index(IndexDocument indexDocument) {
    LOGGER.info("pretend sending index document {} to search service", indexDocument.get("id"));
  }
}
