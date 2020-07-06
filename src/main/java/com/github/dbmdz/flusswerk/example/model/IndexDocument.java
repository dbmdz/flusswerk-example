package com.github.dbmdz.flusswerk.example.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class IndexDocument {

  private final Map<String, Object> fields;

  public IndexDocument() {
    fields = new HashMap<>();
  }

  public void put(String field, Object value) {
    fields.put(field, value);
  }

  public Object get(String field) {
    return fields.get(field);
  }

  public Map<String, Object> getFields() {
    return fields;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IndexDocument that = (IndexDocument) o;
    return fields.equals(that.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fields);
  }
}
