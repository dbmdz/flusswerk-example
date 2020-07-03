package com.github.dbmdz.flusswerk.example.messages;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("The IndexMessage")
class IndexMessageTest {

  @DisplayName("should be created from JSON")
  @Test
  void shouldBeCreatedFromJson() throws JsonProcessingException {
    var json = "{ \"itemId\": \"42\" }";
    var actual = new ObjectMapper().readValue(json, IndexMessage.class);
    assertThat(actual.getItemId()).isEqualTo("42");
  }

}