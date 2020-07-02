package com.github.dbmdz.flusswerk.example.messages;

import static java.util.Objects.requireNonNull;

import com.github.dbmdz.flusswerk.framework.model.Message;
import java.util.Objects;

public class IndexMessage extends Message {

  private final String itemId;

  public IndexMessage(String itemId) {
    this.itemId = requireNonNull(itemId);
  }

  public String getItemId() {
    return itemId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IndexMessage that = (IndexMessage) o;
    return Objects.equals(itemId, that.itemId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemId);
  }
}
