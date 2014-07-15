package com.actionbazaar.interfaces.socket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.ContainerProvider;
import javax.websocket.Decoder;
import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ChatServerTest {

    private static final Logger logger
            = Logger.getLogger(ChatServerTest.class.getName());

    private static ChatMessage testMessage;
    private static ChatMessage testReply;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "actionbazaar-test.war")
                .addClass(ChatMessage.class)
                .addClass(ChatServer.class);
    }

    @Test
    public void testChat() {
        try {
            URI uri = new URI("ws://localhost:7001/actionbazaar-test/chat");

            WebSocketContainer container = ContainerProvider.getWebSocketContainer();

            ClientEndpointConfig configuration = ClientEndpointConfig.Builder.create()
                    .decoders(Arrays.<Class<? extends Decoder>>asList(ChatMessage.class))
                    .encoders(Arrays.<Class<? extends Encoder>>asList(ChatMessage.class))
                    .build();

            Endpoint client1 = new Endpoint() {
                @Override
                public void onOpen(Session session, EndpointConfig config) {
                    try {
                        session.addMessageHandler(new MessageHandler.Whole<ChatMessage>() {
                            @Override
                            public void onMessage(ChatMessage message) {
                                testReply = message;
                            }
                        });
                        session.getBasicRemote().sendObject(
                                new ChatMessage("rrahman", "Test message"));
                    } catch (IOException | EncodeException e) {
                        logger.log(Level.SEVERE, "Error in chat client", e);
                    }
                }
            };

            Endpoint client2 = new Endpoint() {
                @Override
                public void onOpen(final Session session, final EndpointConfig config) {
                    session.addMessageHandler(new MessageHandler.Whole<ChatMessage>() {
                        @Override
                        public void onMessage(ChatMessage message) {
                            try {
                                testMessage = message;
                                session.getBasicRemote().sendObject(new ChatMessage("nrahman", "Test reply"));
                            } catch (IOException | EncodeException ex) {
                                logger.log(Level.SEVERE, "Error responding to message", ex);
                            }
                        }
                    });
                }
            };

            container.connectToServer(client2, configuration, uri);
            container.connectToServer(client1, configuration, uri);

            // Wait for conversation to finish.
            Thread.sleep(2000);

            assertEquals("rrahman", testMessage.getUser());
            assertEquals("Test message", testMessage.getMessage());
            assertEquals("nrahman", testReply.getUser());
            assertEquals("Test reply", testReply.getMessage());
        } catch (URISyntaxException | DeploymentException | IOException | InterruptedException ex) {
            logger.log(Level.SEVERE, "Error connecting to server", ex);
        }
    }
}
