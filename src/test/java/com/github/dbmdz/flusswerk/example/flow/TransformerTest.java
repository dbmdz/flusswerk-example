package com.github.dbmdz.flusswerk.example.flow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.github.dbmdz.flusswerk.example.model.Document;
import com.github.dbmdz.flusswerk.example.model.IndexDocument;
import java.util.Map.Entry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("The Transformer")
class TransformerTest {

  @DisplayName("should fill all fields")
  @Test
  void shouldFillAllFields() {
    var document = new Document("123", "some content");
    var transformer = new Transformer();
    var indexDocument = transformer.apply(document);
    var actual = indexDocument.getFields();
    assertThat(actual).containsOnlyKeys("id", "content");
    assertThat(actual.get("id")).isEqualTo(document.getId());
    assertThat(actual.get("content")).isEqualTo(document.getContent());
  }

}