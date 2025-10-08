package dev.mdz.flusswerk.example.flow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.mdz.flusswerk.example.messages.DemoMessage;
import dev.mdz.flusswerk.example.messages.DoMoreStuffMessage;
import dev.mdz.flusswerk.exceptions.RetryProcessingException;
import dev.mdz.flusswerk.exceptions.StopProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("The Processor")
public class ProcessorTest {

  private DemoProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new DemoProcessor();
  }

  @DisplayName("should process DemoMessage when everything is fine")
  @Test
  public void testProcess() {
    DemoMessage message = new DemoMessage("test-id", DemoMessage.Issue.EVERYTHING_FINE);
    DoMoreStuffMessage result = (DoMoreStuffMessage) processor.apply(message);
    assertThat(processor.apply(message)).isEqualTo(result);
  }

  @DisplayName("should stop processing if data is broken")
  @Test
  public void testStopProcessing() {
    DemoMessage message = new DemoMessage("test-id", DemoMessage.Issue.PERMANENT);
    assertThatThrownBy(() -> processor.apply(message))
        .isInstanceOf(StopProcessingException.class)
        .hasMessageContaining("data is corrupt");
  }

  @DisplayName("should retry processing if backend is unavailable")
  @Test
  public void testRetryProcessing() {
    DemoMessage message = new DemoMessage("test-id", DemoMessage.Issue.TEMPORARY);
    assertThatThrownBy(() -> processor.apply(message))
        .isInstanceOf(RetryProcessingException.class)
        .hasMessageContaining("backend is temporarily unavailable");
  }
}
