package dev.mdz.flusswerk.example;

import dev.mdz.flusswerk.EnableFlusswerk;
import dev.mdz.flusswerk.FlusswerkApplication;
import dev.mdz.flusswerk.example.flow.DemoProcessor;
import dev.mdz.flusswerk.example.messages.DemoMessage;
import dev.mdz.flusswerk.flow.FlowSpec;
import dev.mdz.flusswerk.flow.builder.FlowBuilder;
import dev.mdz.flusswerk.model.IncomingMessageType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFlusswerk
public class Application extends FlusswerkApplication {

    /**
     * This tells Flusswerk which class to use for deserializing incoming messages.
     *
     * @return the incoming message type
     */
    @Bean
    public IncomingMessageType incomingMessageType() {
        return new IncomingMessageType(DemoMessage.class);
    }

    /**
     * This tells Fluswerk how to process messages.
     *
     * @param processor the bean that contains the processing logic
     * @return the flow specification
     */
    @Bean
    public FlowSpec flowSpec(DemoProcessor processor) {
        return FlowBuilder.messageProcessor(DemoMessage.class)
                .process(processor)
                .build();
    }

    /**
     * The main method to start the Spring Boot application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
