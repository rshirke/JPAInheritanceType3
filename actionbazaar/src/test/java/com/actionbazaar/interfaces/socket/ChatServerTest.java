package com.actionbazaar.interfaces.socket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ChatServerTest {

    private static final Logger logger
            = Logger.getLogger(ChatServerTest.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "actionbazaar-test.war")
                .addClass(ChatMessage.class)
                .addClass(ChatServer.class);
    }

    @Test
    public void testChat() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            ClientEndpointConfig clientConfiguration
                    = ClientEndpointConfig.Builder.create().build();

            container.connectToServer(ChatClient.class, clientConfiguration,
                    new URI("ws://localhost:7001/actionbazaar-test/chat"));

            // assertEquals(5, response.getJsonArray("alerts").size());
        } catch (URISyntaxException | DeploymentException | IOException ex) {
            logger.log(Level.SEVERE, "Error connecting to server", ex);
        }
    }

    private class ChatClient extends Endpoint {

        @Override
        public void onOpen(Session session, EndpointConfig config) {
            try {
                ChatMessage message = new ChatMessage("rrahman", "Test Message");
                session.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException ex) {
                logger.log(Level.WARNING, "Error sending message", ex);
            }
        }
    }
}
