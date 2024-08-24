package com.vikas.chat_app.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
// Purpose: @Controller is used for traditional Spring controllers, handling HTTP requests and responses, while @RestController is specifically designed for RESTful web services, automatically serializing return objects into HTTP responses.
// ResponseBody annotation: In @Controller, you need to manually add @ResponseBody to each request handling method that returns a response. In contrast, @RestController automatically applies the @ResponseBody annotation, so you don’t need to add it explicitly.
// Usage: @Controller is typically used in combination with @RequestMapping annotation on request handling methods, while @RestController is used as a class-level annotation, making it a convenient choice for creating RESTful web services.
public class ChatController {
    
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        return chatMessage;
    }
    
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
        @Payload ChatMessage chatMessage,
        SimpMessageHeaderAccessor headerAccessor
    ){
        // Add username in websocket Session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
