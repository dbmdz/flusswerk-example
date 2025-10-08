package dev.mdz.flusswerk.example.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mdz.flusswerk.model.Message;

/**
 * This is the example message to showcase the main Flusswerk features. Please note that it has to extend the Message
 * class.
 */
public class DemoMessage extends Message {

    public enum Issue {
        EVERYTHING_FINE,
        TEMPORARY,
        PERMANENT
    }

    private String id;
    private Issue issue;

    public DemoMessage(@JsonProperty("id") String id, @JsonProperty("issue") Issue issue) {
        this.id = id;
        if (issue == null) {
            issue = Issue.EVERYTHING_FINE;
        } else {
            this.issue = issue;
        }
    }

    public String getId() {
        return id;
    }

    public Issue getIssue() {
        return issue;
    }
}
