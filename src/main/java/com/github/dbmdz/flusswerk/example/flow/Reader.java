package com.github.dbmdz.flusswerk.example.flow;

import com.github.dbmdz.flusswerk.example.messages.IndexMessage;
import com.github.dbmdz.flusswerk.example.model.Document;
import com.github.dbmdz.flusswerk.framework.exceptions.StopProcessingException;
import java.io.IOException;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class Reader implements Function<IndexMessage, Document> {

  @Override
  public Document apply(IndexMessage indexMessage) {
    Document document;
    try {
      document = loadDocument(indexMessage.getItemId());
    } catch (IOException exception) {
      throw new StopProcessingException(
              "Could not load document for id {}", indexMessage.getItemId())
          .causedBy(exception);
    }
    return document;
  }

  private Document loadDocument(String itemId) throws IOException {
    // pretend to load document from disk
    return new Document(itemId, String.format("Content of %s", itemId));
  }
}
