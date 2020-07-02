package com.github.dbmdz.flusswerk.example.messages;

import static java.util.Objects.requireNonNull;

import com.github.dbmdz.flusswerk.framework.model.Message;
import java.util.Objects;

public class RefreshWebsiteMessage extends Message {

  private final String itemId;
  private final String source;

  public RefreshWebsiteMessage(String itemId, String source) {
    this.itemId = requireNonNull(itemId);
    this.source = requireNonNull(source);
  }

  public String getItemId() {
    return itemId;
  }

  public String getSource() {
    return source;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RefreshWebsiteMessage that = (RefreshWebsiteMessage) o;
    return itemId.equals(that.itemId) && source.equals(that.source);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemId, source);
  }
}
