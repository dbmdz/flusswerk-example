package dev.mdz.flusswerk.example.messages;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("The DemoMessage")
public class DemoMessageTest {

    @DisplayName("should create a DemoMessage with correct id and issue")
    @Test
    public void testCreateDemoMessage() {
        DemoMessage message = new DemoMessage("test-id", DemoMessage.Issue.EVERYTHING_FINE);
        assertThat(message.getId()).isEqualTo("test-id");
        assertThat(message.getIssue()).isEqualTo(DemoMessage.Issue.EVERYTHING_FINE);
    }

    @DisplayName("should default issue to EVERYTHING_FINE when null is provided")
    @Test
    public void testDefaultIssue() {
        DemoMessage messageWithNullIssue = new DemoMessage("test-id", null);
        assertThat(messageWithNullIssue.getIssue()).isEqualTo(DemoMessage.Issue.EVERYTHING_FINE);
    }


}
