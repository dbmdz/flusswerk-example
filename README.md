# Flusswerk Application Example 
This application simulates a simple indexing application to show the Flusswerk framework in action.

## Overview

The data processing logic is split in three components:

The [`Reader`](src/main/java/com/github/dbmdz/flusswerk/example/flow/Reader.java) receives a message from RabbitMQ and loads the corresponding document (as instance of `Document`).

```java
@Component
public class Reader implements Function<IndexMessage, Document> {

  @Override
  public Document apply(IndexMessage indexMessage) {
    Document document;
    try {
      document = loadDocument(indexMessage.getItemId());
    } catch (IOException exception) {
      throw new StopProcessingException(
              "Could not load document for id {}", indexMessage.getItemId())
          .causedBy(exception);
    }
    return document;
  }

  private Document loadDocument(String itemId) throws IOException {
      // ...
  }

}
```

The [`Transformer`](src/main/java/com/github/dbmdz/flusswerk/example/flow/Transformer.java) then takes the document and builds the required data for the Indexing API (an `IndexDocument`):

```java
@Component
public class Transformer implements Function<Document, IndexDocument> {

  @Override
  public IndexDocument apply(Document document) {
    IndexDocument indexDocument = new IndexDocument();
    // ...
    return indexDocument;
  }
}
```


The [`Writer`](src/main/java/com/github/dbmdz/flusswerk/example/flow/Writer.java) finally takes the processed data, writes it to the Indexing API and sends messages to notify the next processing application.

```java
@Component
public class Writer implements Function<IndexDocument, Message> {

  private static final Logger LOGGER = LoggerFactory.getLogger(Writer.class);

  @Override
  public Message apply(IndexDocument indexDocument) {
    String id = (String) indexDocument.get("id");
    try {
      sendToSearchService(indexDocument);
    } catch (Exception exception) {
      throw new RetryProcessingException(
              "Could not index document for id %s, will try again later", id)
          .causedBy(exception);
    }
    return new RefreshWebsiteMessage(id, "search");
  }
}
```

## Try yourself:

To try yourself, get the repository and RabbitMQ-Server:

```bash
$ git clone https://github.com/dbmdz/flusswerk-example.git
$ cd flusswerk-example
$ docker-compose up
```

Then start the `flusswerk-example` Application from your IDE and open the RabbitMQ-Management UI at http://localhost:15672 (Login in as `guest`/`guest`). 

Drop the following message into the queue `search.index`:

```json
{ "itemId": "42", "tracingId": "12345" }
```

In the queue `search.publish`, you will find the outgoing message send by the `Writer`:

```json
{
    "envelope":{ ... }, // Flusswerk metadata
    "tracingId":"12345",
    "itemId":"42",
    "source":"search"
}
```
