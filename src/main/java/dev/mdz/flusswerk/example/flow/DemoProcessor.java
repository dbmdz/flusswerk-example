package dev.mdz.flusswerk.example.flow;

import dev.mdz.flusswerk.example.messages.DemoMessage;
import dev.mdz.flusswerk.example.messages.DoMoreStuffMessage;
import dev.mdz.flusswerk.exceptions.RetryProcessingException;
import dev.mdz.flusswerk.exceptions.StopProcessingException;
import dev.mdz.flusswerk.model.Message;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/** This is the main processing logic of this example application. */
@Component
public class DemoProcessor implements Function<DemoMessage, Message> {

    private static final Logger log = LoggerFactory.getLogger(DemoProcessor.class);

    @Override
    public Message apply(DemoMessage demoMessage) {
        switch (demoMessage.getIssue()) {
            case EVERYTHING_FINE:
                log.info("Processing message with id {}", demoMessage.getId());
                return new DoMoreStuffMessage(demoMessage.getId());
            case PERMANENT:
                throw new StopProcessingException(
                        "Oh no, the data is corrupt. This is a permanent error. We cannot process this message.");
            case TEMPORARY:
                throw new RetryProcessingException(
                        "Oh no, our backend is temporarily unavailable. This is a error that might go away. Let's try again later.");
            default:
                throw new IllegalStateException("This should never happen!");
        }
    }
}
