# Flusswerk Application Example 
## Overview

This application demonstrates the Flusswerk framework in action. A Flusswerk application typically takes an incoming message from a RabbitMQ queue, does something, and then may send any number of messages to other topics for further processing.

The data processing logic is in [DemoProcessor](src/main/java/dev/mdz/flusswerk/example/flow/DemoProcessor.java). Usually all kinds of things can go wrong when processing data: Data might be corrupt, backends fail,... Flusswerk provides a simple way to handle these error cases with special exceptions. 

We simulate the three general error cases with the field `issue` in the incoming message:

| Case                            | Field value | Behavior |
| ------------------------------- | --------------------------- | --------- |
| No error, everything just works | `EVERYTHING_FINE` (default) | This is the usual case, everything works as expected, data is processed. |
| Temporary error                 | `TEMPORARY`                 | Sometimes there are issues that will resolve themselves, and we can try processing the message again later. A typical example would be a temporary network issue or an API service that is restarting. |
| Permanent error                 | `PERMANENT`                 | In some cases it is clear that trying again later won't make a difference, e.g. if the data is corrupt. Then we stop processing for good.                                                              |

## Try yourself:

To try yourself, get the repository and RabbitMQ-Server:

```bash
$ git clone https://github.com/dbmdz/flusswerk-example.git
$ cd flusswerk-example
$ docker-compose up -d
```

Then start the `flusswerk-example` Application from your IDE and open the RabbitMQ-Management UI at http://localhost:15672 (Login in as `guest`/`guest`). 

Drop the following message into the queue `incoming`:

```json
{ "id": "42" }
```

You now should find a message in the queue `next`.

Now try the error cases:

```json
{ "id": "42", "issue": "TEMPORARY" }
```

and

```json
{ "id": "42", "issue": "PERMANENT" }
```
