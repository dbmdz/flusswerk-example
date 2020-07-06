package com.github.dbmdz.flusswerk.example.flow;

import static java.util.Objects.requireNonNull;

import com.github.dbmdz.flusswerk.example.messages.IndexMessage;
import com.github.dbmdz.flusswerk.example.model.Document;
import com.github.dbmdz.flusswerk.example.stubs.DocumentRepository;
import com.github.dbmdz.flusswerk.framework.exceptions.StopProcessingException;
import java.io.IOException;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class Reader implements Function<IndexMessage, Document> {

  private final DocumentRepository documentRepository;

  public Reader(DocumentRepository documentRepository) {
    this.documentRepository = requireNonNull(documentRepository);
  }

  @Override
  public Document apply(IndexMessage indexMessage) {
    Document document;
    try {
      document = documentRepository.loadDocument(indexMessage.getItemId());
    } catch (IOException exception) {
      throw new StopProcessingException(
              "Could not load document for id {}", indexMessage.getItemId())
          .causedBy(exception);
    }
    return document;
  }
}
