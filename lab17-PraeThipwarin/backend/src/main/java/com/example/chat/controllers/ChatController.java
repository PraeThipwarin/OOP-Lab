package com.example.chat.controllers;


import com.example.chat.configs.WebSocketEventListener;
import com.example.chat.dtos.CreateChatMessageBody;
import com.example.chat.models.ChatMessage;
import com.example.chat.models.MessageType;
import com.example.chat.models.User;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import static com.example.chat.configs.WebSocketEventListener.activeUsers;

@Controller
@AllArgsConstructor
public class ChatController {
    private final SimpMessageSendingOperations messagingTemplate;


    @MessageMapping("/chat/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(CreateChatMessageBody createChatMessageBody){
        String username = createChatMessageBody.getSender();
        String message = createChatMessageBody.getMessage();
        MessageType messageType = createChatMessageBody.getType();

        return ChatMessage.buildChatmessage(message, username, messageType);
    }

    @MessageMapping("/chat/addUser")
    @SendToUser("/queue/connected")
    public User addUser(CreateChatMessageBody createChatMessageBody , SimpMessageHeaderAccessor headerAccessor){
        String username = createChatMessageBody.getSender();
        String message = createChatMessageBody.getMessage();
        MessageType messageType = createChatMessageBody.getType();


        headerAccessor.getSessionAttributes().put("username" , username);
        WebSocketEventListener.addUser(username);
        messagingTemplate.convertAndSend("/topic/users", WebSocketEventListener.getActiveUserCount()); // Notify frontend
        messagingTemplate.convertAndSend("/topic/messages" , ChatMessage.buildChatmessage(message,username,messageType));


        return new User(username);
    }

}
