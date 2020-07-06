package com.github.dbmdz.flusswerk.example.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("The IndexDocument")
class IndexDocumentTest {

  private static IndexDocument copy(IndexDocument indexDocument) {
    var result = new IndexDocument();
    indexDocument.getFields().forEach(result::put);
    return result;
  }

  @DisplayName("should be equal to an identical document")
  @Test
  void shouldBeEqualToIdenticalDocument() {
    var expected = new IndexDocument();
    expected.put("id", "123");
    var actual = copy(expected);
    assertThat(actual).isEqualTo(expected);
  }

  @DisplayName("should be equal to an identical message")
  @Test
  void shouldHaveSameHashCodeAsIdenticalMessage() {
    var expected = new IndexDocument();
    expected.put("id", "123");
    var actual = copy(expected);
    assertThat(actual.hashCode()).isEqualTo(expected.hashCode());
  }

  @DisplayName("should not be equal if fields are different")
  @Test
  void shouldNotBeEqualIfFieldIsDifferent() {
    var document1 = new IndexDocument();
    document1.put("id", "123");
    var document2 = new IndexDocument();
    document2.put("id", "999");
    assertThat(document1).isNotEqualTo(document2);
  }

  @DisplayName("should not have same hashCode if field is different")
  @Test
  void shouldNotHaveSameHashCodeIfFieldIsDifferent() {
    var document1 = new IndexDocument();
    document1.put("id", "123");
    var document2 = new IndexDocument();
    document2.put("id", "999");
    assertThat(document1.hashCode()).isNotEqualTo(document2.hashCode());
  }
}
