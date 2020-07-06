package com.github.dbmdz.flusswerk.example.flow;

import static java.util.Objects.requireNonNull;

import com.github.dbmdz.flusswerk.example.IndexClient;
import com.github.dbmdz.flusswerk.example.messages.RefreshWebsiteMessage;
import com.github.dbmdz.flusswerk.example.model.IndexDocument;
import com.github.dbmdz.flusswerk.framework.exceptions.RetryProcessingException;
import com.github.dbmdz.flusswerk.framework.model.Message;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class Writer implements Function<IndexDocument, Message> {

  private final IndexClient indexClient;

  public Writer(IndexClient indexClient) {
    this.indexClient = requireNonNull(indexClient);
  }

  @Override
  public Message apply(IndexDocument indexDocument) {
    String id = (String) indexDocument.get("id");
    try {
      indexClient.index(indexDocument);
    } catch (Exception exception) {
      throw new RetryProcessingException(
              "Could not index document for id %s, will try again later", id)
          .causedBy(exception);
    }
    return new RefreshWebsiteMessage(id, "search");
  }

}
