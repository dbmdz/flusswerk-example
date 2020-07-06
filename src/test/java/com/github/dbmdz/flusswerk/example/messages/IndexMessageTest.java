package com.github.dbmdz.flusswerk.example.messages;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("The IndexMessage")
class IndexMessageTest {

  private static Stream<Arguments> differentMessages() {
    return Stream.of(Arguments.of("itemId", new IndexMessage("123"), new IndexMessage("789")));
  }

  @DisplayName("should be created from JSON")
  @Test
  void shouldBeCreatedFromJson() throws JsonProcessingException {
    var json = "{ \"itemId\": \"42\" }";
    var actual = new ObjectMapper().readValue(json, IndexMessage.class);
    assertThat(actual.getItemId()).isEqualTo("42");
  }

  @DisplayName("should be equal to an identical message")
  @Test
  void shouldBeEqualToIdenticalMessage() {
    var expected = new IndexMessage("123");
    var actual = new IndexMessage(expected.getItemId());
    assertThat(actual).isEqualTo(expected);
  }

  @DisplayName("should be equal to an identical message")
  @Test
  void shouldHaveSameHashCodeAsIdenticalMessage() {
    var expected = new IndexMessage("123");
    var actual = new IndexMessage(expected.getItemId());
    assertThat(actual.hashCode()).isEqualTo(expected.hashCode());
  }

  @DisplayName("should not be equal if field is different")
  @ParameterizedTest(name = "{0} is included in equals")
  @MethodSource("differentMessages")
  void shouldNotBeEqualIfFieldIsDifferent(
      String differentField, IndexMessage message1, IndexMessage message2) {
    assertThat(message1).isNotEqualTo(message2);
  }

  @DisplayName("should not have same hashCode if field is different")
  @ParameterizedTest(name = "{0} is included in hashCode")
  @MethodSource("differentMessages")
  void shouldNotHaveSameHashCodeIfFieldIsDifferent(
      String differentField, IndexMessage message1, IndexMessage message2) {
    assertThat(message1.hashCode()).isNotEqualTo(message2.hashCode());
  }
}
