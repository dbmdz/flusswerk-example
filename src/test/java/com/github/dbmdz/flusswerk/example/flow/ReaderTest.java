package com.github.dbmdz.flusswerk.example.flow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.dbmdz.flusswerk.example.DocumentRepository;
import com.github.dbmdz.flusswerk.example.messages.IndexMessage;
import com.github.dbmdz.flusswerk.example.model.Document;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("The Reader")
class ReaderTest {

  @DisplayName("should load document for id")
  @Test
  void apply() throws IOException {
    DocumentRepository documentRepository = mock(DocumentRepository.class);
    Reader reader = new Reader(documentRepository);
    Document expected = new Document("1234", "Once upon a time…");
    when(documentRepository.loadDocument(expected.getId())).thenReturn(expected);
    Document actual = reader.apply(new IndexMessage(expected.getId()));
    assertThat(actual).isEqualTo(expected);
  }

}