
package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatMessage.class);



    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        //logger.info(ChatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }


/*If you recall from the websocket configuration, all the messages sent from clients with a destination
    starting with /app will be routed to these message handling methods annotated with @MessageMapping.

    For example, a message with destination /app/chat.sendMessage will be routed to the sendMessage() method,
    and a message with destination /app/chat.addUser will be routed to the addUser() method.*/

}

