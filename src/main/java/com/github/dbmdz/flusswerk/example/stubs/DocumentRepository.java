package com.github.dbmdz.flusswerk.example.stubs;

import com.github.dbmdz.flusswerk.example.model.Document;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DocumentRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(DocumentRepository.class);

  public Document loadDocument(String itemId) throws IOException {
    // pretend to load document from disk
    LOGGER.info("Pretend loading document with id '{}' from disk.", itemId);
    return new Document(itemId, "Once upon a time…");
  }
}
