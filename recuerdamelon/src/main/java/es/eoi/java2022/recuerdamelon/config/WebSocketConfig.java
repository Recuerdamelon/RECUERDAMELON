
package es.eoi.java2022.recuerdamelon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//WebSocket is a two ways COMMUNICATION Protocol (Server-client)
@Configuration
@EnableWebSocketMessageBroker //Enable our web socket server
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
                                //Interface providing configuration methods
    @Override

    //STOMP: Simple Text Oriented MESSAGING Protocol: Format and rules message exchanging
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //Address messages to a route
        registry.addEndpoint("/ws").withSockJS();//websocket endpoint where our client can connect
                                            //SockJS enable use in every browser
    }

    @Override //MessageBroker: route messages from one client to another
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        //message destinations starting with "/app" should be routed to message-handling methods
        registry.enableSimpleBroker("/topic");
        //message destinations starting with "/topic" should be routed to the MessageBroker
        //MessageBroker will broadcast messages to all clients subscribed to a particular topic
    }
}

