package com.github.dbmdz.flusswerk.example.messages;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RefreshWebsiteMessageTest {

  private static Stream<Arguments> differentMessages() {
    return Stream.of(
        Arguments.of(
            "itemId",
            new RefreshWebsiteMessage("123", "source"),
            new RefreshWebsiteMessage("789", "source")),
        Arguments.of(
            "source",
            new RefreshWebsiteMessage("123", "source 1"),
            new RefreshWebsiteMessage("123", "source 2")));
  }

  @DisplayName("should be equal to an identical message")
  @Test
  void shouldBeEqualToIdenticalMessage() {
    var expected = new RefreshWebsiteMessage("123", "source");
    var actual = new RefreshWebsiteMessage(expected.getItemId(), expected.getSource());
    assertThat(actual).isEqualTo(expected);
  }

  @DisplayName("should be equal to an identical message")
  @Test
  void shouldHaveSameHashCodeAsIdenticalMessage() {
    var expected = new RefreshWebsiteMessage("123", "source");
    var actual = new RefreshWebsiteMessage(expected.getItemId(), expected.getSource());
    assertThat(actual.hashCode()).isEqualTo(expected.hashCode());
  }

  @DisplayName("should not be equal if field is different")
  @ParameterizedTest(name = "{0} is included in equals")
  @MethodSource("differentMessages")
  void shouldNotBeEqualIfFieldIsDifferent(
      String differentField, RefreshWebsiteMessage message1, RefreshWebsiteMessage message2) {
    assertThat(message1).isNotEqualTo(message2);
  }

  @DisplayName("should not have same hashCode if field is different")
  @ParameterizedTest(name = "{0} is included in hashCode")
  @MethodSource("differentMessages")
  void shouldNotHaveSameHashCodeIfFieldIsDifferent(
      String differentField, RefreshWebsiteMessage message1, RefreshWebsiteMessage message2) {
    assertThat(message1.hashCode()).isNotEqualTo(message2.hashCode());
  }
}
