package com.github.dbmdz.flusswerk.example.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("The Document")
class DocumentTest {
  private static Stream<Arguments> differentDocuments() {
    return Stream.of(
        Arguments.of("id", new Document("123", "content"), new Document("789", "content")),
        Arguments.of(
            "content", new Document("123", "content 1"), new Document("123", "content 2")));
  }

  @DisplayName("should be equal to an identical document")
  @Test
  void shouldBeEqualToIdenticalDocument() {
    var expected = new Document("123", "content");
    var actual = new Document(expected.getId(), expected.getContent());
    assertThat(actual).isEqualTo(expected);
  }

  @DisplayName("should be equal to an identical message")
  @Test
  void shouldHaveSameHashCodeAsIdenticalMessage() {
    var expected = new Document("123", "content");
    var actual = new Document(expected.getId(), expected.getContent());
    assertThat(actual.hashCode()).isEqualTo(expected.hashCode());
  }

  @DisplayName("should not be equal if field is different")
  @ParameterizedTest(name = "{0} is included in equals")
  @MethodSource("differentDocuments")
  void shouldNotBeEqualIfFieldIsDifferent(String field, Document document1, Document document2) {
    assertThat(document1).isNotEqualTo(document2);
  }

  @DisplayName("should not have same hashCode if field is different")
  @ParameterizedTest(name = "{0} is included in hashCode")
  @MethodSource("differentDocuments")
  void shouldNotHaveSameHashCodeIfFieldIsDifferent(
      String field, Document document1, Document document2) {
    assertThat(document1.hashCode()).isNotEqualTo(document2.hashCode());
  }
}
