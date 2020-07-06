package com.github.dbmdz.flusswerk.example.stubs;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("The DocumentRepository")
class DocumentRepositoryTest {

  @DisplayName("The Document repository should pretend to load document with id")
  @Test
  void shouldPretendToLoadDocumentWithId() throws IOException {
    var documentRepository = new DocumentRepository();
    var expected = "123123123";
    var document = documentRepository.loadDocument(expected);
    assertThat(document.getId()).isEqualTo(expected);
  }
}
