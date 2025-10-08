package dev.mdz.flusswerk.example.messages;

import dev.mdz.flusswerk.model.Message;

/**
 * This is a example of a message that could be sent after processing a DemoMessage to notify other
 * processing jobs to do more stuff. Please note that this has to extend the Message class.
 */
public class DoMoreStuffMessage extends Message {
  private String id;

  public DoMoreStuffMessage(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
