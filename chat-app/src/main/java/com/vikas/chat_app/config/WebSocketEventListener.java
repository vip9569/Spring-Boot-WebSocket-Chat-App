package com.vikas.chat_app.config;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.vikas.chat_app.chat.ChatMessage;
import com.vikas.chat_app.chat.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
// Use @Component when:
// You want a class to be managed by Spring and be eligible for dependency injection.
// You want Spring to create an instance of the class at runtime and inject it into other beans.
// The class represents a service, repository, or utility that needs to be exposed to other parts of the application.
        
@RequiredArgsConstructor

@Slf4j
// SLF4J (Simple Logging Facade for Java) is a logging facade that allows developers to write logging code without being tied to a specific logging framework. In a Spring Boot application, SLF4J acts as a bridge between the logging API and a specific logging implementation, such as Logback or Log4j2.

// When you annotate a class or method with @Slf4j, you’re instructing the compiler to generate a private static field named log of type org.slf4j.Logger. This field can then be used to log messages at various levels (DEBUG, INFO, WARN, ERROR, etc.).

// The benefits of using @Slf4j in Spring Boot include:

// Decoupling logging code from the underlying logging framework
// Flexibility to switch between different logging frameworks (e.g., Logback, Log4j2, java.util.logging) without modifying the logging code
// Simplified logging configuration, as the SLF4J facade handles the underlying logging implementation
// In the provided snippets, @Slf4j is used to generate a log field, which can be used for logging purposes. For example, log.info("Some message"); would log an informational message.

// To use @Slf4j in your Spring Boot application, you’ll need to:

// Add the SLF4J API dependency to your project’s pom.xml file (if you’re using Maven) or build.gradle file (if you’re using Gradle).
// Choose a logging implementation (e.g., Logback or Log4j2) and add its dependency to your project.
// Configure the logging implementation in your application’s application.properties or logback.xml file.
// Annotate the classes or methods that require logging with @Slf4j.

public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null){
            log.info("User Disconnected : {}", username);
            var chatMessage = ChatMessage.builder()
                        .type(MessageType.LEAVE)
                        .sender(username)
                        .build();
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
