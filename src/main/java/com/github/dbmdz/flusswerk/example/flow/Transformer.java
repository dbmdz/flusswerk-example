package com.github.dbmdz.flusswerk.example.flow;

import com.github.dbmdz.flusswerk.example.model.Document;
import com.github.dbmdz.flusswerk.example.model.IndexDocument;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class Transformer implements Function<Document, IndexDocument> {

  @Override
  public IndexDocument apply(Document document) {
    IndexDocument indexDocument = new IndexDocument();
    indexDocument.put("id", document.getId());
    indexDocument.put("content", document.getContent());
    return indexDocument;
  }
}
