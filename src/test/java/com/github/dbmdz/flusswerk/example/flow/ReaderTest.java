package com.github.dbmdz.flusswerk.example.flow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.dbmdz.flusswerk.example.messages.IndexMessage;
import com.github.dbmdz.flusswerk.example.model.Document;
import com.github.dbmdz.flusswerk.example.stubs.DocumentRepository;
import com.github.dbmdz.flusswerk.framework.exceptions.StopProcessingException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("The Reader")
class ReaderTest {

  private DocumentRepository documentRepository;
  private Reader reader;

  @BeforeEach
  void setUp() {
    documentRepository = mock(DocumentRepository.class);
    reader = new Reader(documentRepository);
  }

  @DisplayName("should load document for id")
  @Test
  void apply() throws IOException {
    Document expected = new Document("1234", "Once upon a time…");
    when(documentRepository.loadDocument(expected.getId())).thenReturn(expected);
    Document actual = reader.apply(new IndexMessage(expected.getId()));
    assertThat(actual).isEqualTo(expected);
  }

  @DisplayName("should stop processing on error")
  @Test
  void shouldStopProcessingOnError() throws IOException {
    doThrow(IOException.class).when(documentRepository).loadDocument("123");
    assertThatExceptionOfType(StopProcessingException.class)
        .isThrownBy(() -> reader.apply(new IndexMessage("123")));
  }
}
