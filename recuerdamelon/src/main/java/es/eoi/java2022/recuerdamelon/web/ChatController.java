
package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.data.entity.ChatMessage;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.dto.MessagesDTO;
import es.eoi.java2022.recuerdamelon.service.MessagesService;
import es.eoi.java2022.recuerdamelon.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatMessage.class);



    /*@MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        logger.info("ChatMessage.MessageType.");
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        chatMessage.setSender("Juan");
        logger.info("Este es el usuario");
        logger.info(chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }*/


/*If you recall from the websocket configuration, all the messages sent from clients with a destination
    starting with /app will be routed to these message handling methods annotated with @MessageMapping.

    For example, a message with destination /app/chat.sendMessage will be routed to the sendMessage() method,
    and a message with destination /app/chat.addUser will be routed to the addUser() method.*/

    //----------carlos upload-------------//

    private final MessagesService messagesService;
    private final UserService userService;

    public ChatController(MessagesService messagesService, UserService userService) {
        this.messagesService = messagesService;
        this.userService = userService;
    }

    @GetMapping("/salachat")
    public String get(Model model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.println("user.getUsername() = " + user.getUsername());
//        ChatMessage chatMessage = new ChatMessage();
//        model.addAttribute("chat", chatMessage);
        model.addAttribute("user", user);
        return "salachat";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")//Añadir variable a la ruta del chat->@SendTo("/topic/public/{roomId}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
//        messagesDTO.setContent(chatMessage.getContent());
//        messagesDTO.setSender(chatMessage.getSender());
//        messagesDTO.setType(chatMessage.getType());
        // Añadir room messagesService.save(messagesDTO);
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


//
//    @Transactional
//    @PostMapping("/salachat")
//    public String save(MessagesDTO messagesDTO) {
//        this.messagesService.save(messagesDTO);
//        return "salachat";
//    }


    ////        MessagesDTO messagesDTO = new MessagesDTO();
////        User user = new User();
////        String mChat = chatMessage.getContent();
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        String username = authentication.getName();
////        User sender = userService.findByUsername(username);
////        messagesDTO.setMessage(mChat);
////        messagesDTO.setUser(sender);
////        messagesDTO.setUserId(sender.getId());
////        messagesService.save(messagesDTO);
}

