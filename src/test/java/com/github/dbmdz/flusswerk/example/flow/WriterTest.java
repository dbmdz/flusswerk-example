package com.github.dbmdz.flusswerk.example.flow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.github.dbmdz.flusswerk.example.messages.RefreshWebsiteMessage;
import com.github.dbmdz.flusswerk.example.model.IndexDocument;
import com.github.dbmdz.flusswerk.example.stubs.IndexClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("The Writer")
class WriterTest {

  private IndexClient indexClient;
  private Writer writer;

  @BeforeEach
  void setUp() {
    indexClient = mock(IndexClient.class);
    writer = new Writer(indexClient);
  }

  @DisplayName("should send document to search service")
  @Test
  void shouldSendDocumentToSearchService() {
    var document = new IndexDocument();
    document.put("id", "123");
    writer.apply(document);
    verify(indexClient).index(document);
  }

  @DisplayName("should send message to next data processing step")
  @Test
  void shouldSendMessageToNextDataProcessingStep() {
    var document = new IndexDocument();
    document.put("id", "123");
    var actual = (RefreshWebsiteMessage) writer.apply(document);
    assertThat(actual.getItemId()).isEqualTo(document.get("id"));
  }
}
