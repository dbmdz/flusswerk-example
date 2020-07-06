package com.github.dbmdz.flusswerk.example;

import com.github.dbmdz.flusswerk.example.flow.Reader;
import com.github.dbmdz.flusswerk.example.flow.Transformer;
import com.github.dbmdz.flusswerk.example.flow.Writer;
import com.github.dbmdz.flusswerk.example.messages.IndexMessage;
import com.github.dbmdz.flusswerk.example.model.Document;
import com.github.dbmdz.flusswerk.example.model.IndexDocument;
import com.github.dbmdz.flusswerk.framework.flow.FlowSpec;
import com.github.dbmdz.flusswerk.framework.flow.builder.FlowBuilder;
import com.github.dbmdz.flusswerk.framework.model.IncomingMessageType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlusswerkConfig {

  @Bean
  public IncomingMessageType incomingMessageType() {
    return new IncomingMessageType(IndexMessage.class);
  }

  @Bean
  public FlowSpec<IndexMessage, Document, IndexDocument> flowSpec(
      Reader reader, Transformer transformer, Writer writer) {
    return FlowBuilder.flow(IndexMessage.class, Document.class, IndexDocument.class)
        .reader(reader)
        .transformer(transformer)
        .writerSendingMessage(writer)
        .build();
  }
}
