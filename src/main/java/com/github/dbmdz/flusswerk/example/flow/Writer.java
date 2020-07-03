package com.github.dbmdz.flusswerk.example.flow;

import com.github.dbmdz.flusswerk.example.messages.RefreshWebsiteMessage;
import com.github.dbmdz.flusswerk.example.model.IndexDocument;
import com.github.dbmdz.flusswerk.framework.exceptions.RetryProcessingException;
import com.github.dbmdz.flusswerk.framework.model.Message;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Writer implements Function<IndexDocument, Message> {

  private static final Logger LOGGER = LoggerFactory.getLogger(Writer.class);

  @Override
  public Message apply(IndexDocument indexDocument) {
    String id = (String) indexDocument.get("id");
    try {
      sendToSearchService(indexDocument);
    } catch (Exception exception) {
      throw new RetryProcessingException(
              "Could not index document for id %s, will try again later", id)
          .causedBy(exception);
    }
    return new RefreshWebsiteMessage(id, "search");
  }

  private void sendToSearchService(IndexDocument indexDocument) {
    LOGGER.info("pretend sending index document {} to search service",
        indexDocument.get("id"));
  }
}
