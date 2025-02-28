package com.example.chat.configs;

import com.example.chat.models.ChatMessage;
import com.example.chat.models.MessageType;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@AllArgsConstructor
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messagingTemplate;
    public static final Set<String> activeUsers = ConcurrentHashMap.newKeySet(); // Thread-safe set

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username  != null) {
            activeUsers.remove(username);
            messagingTemplate.convertAndSend("/topic/users", activeUsers.size());
            ChatMessage chatMessage = ChatMessage.buildChatmessage(username + "has left the chat." , username , MessageType.LEAVE);
            messagingTemplate.convertAndSend("/topic/messages" , chatMessage);
        }
    }

    public static void addUser(String username) {
        activeUsers.add(username);
    }

    public static int getActiveUserCount() {
        return activeUsers.size();
    }
}
