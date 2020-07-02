package com.github.dbmdz.flusswerk.example.model;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class Document {

  private final String id;
  private final String content;

  public Document(String id, String content) {
    this.id = requireNonNull(id);
    this.content = requireNonNull(content);
  }

  public String getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Document document = (Document) o;
    return id.equals(document.id) && content.equals(document.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, content);
  }
}
