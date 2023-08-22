package matthew.bot.listener;

import matthew.bot.listeners.EventListener;
import matthew.bot.service.handler.Handler;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EventListenerTest {
    @Mock
    Handler handler;
    @Mock
    MessageReceivedEvent messageReceivedEvent;
    @Mock
    User user;
    @Mock
    Message message;

    @InjectMocks
    EventListener eventListener;

    @BeforeEach
    void setUp() {
        eventListener = new EventListener(List.of(handler));
    }

    @Test
    void botSendMessageTest() {
        Mockito.when(messageReceivedEvent.getAuthor()).thenReturn(user);
        Mockito.when(messageReceivedEvent.getAuthor().isBot()).thenReturn(true);
        eventListener.onMessageReceived(messageReceivedEvent);

        Mockito.verify(messageReceivedEvent, Mockito.times(0)).getMessage();
    }

    @Test
    void MessageReceiveTest() {
        String receivedMessage = "!ping";
        Mockito.when(messageReceivedEvent.getAuthor()).thenReturn(user);
        Mockito.when(messageReceivedEvent.getAuthor().isBot()).thenReturn(false);
        Mockito.when(messageReceivedEvent.getMessage()).thenReturn(message);
        Mockito.when(message.getContentRaw()).thenReturn(receivedMessage);

        eventListener.onMessageReceived(messageReceivedEvent);
        Mockito.verify(handler, Mockito.times(1)).handle(messageReceivedEvent);

    }

    @Test
    void IncorrectMessageReceiveTest() {
        String receivedMessage = "ERROR";
        Mockito.when(messageReceivedEvent.getAuthor()).thenReturn(user);
        Mockito.when(messageReceivedEvent.getAuthor().isBot()).thenReturn(false);
        Mockito.when(messageReceivedEvent.getMessage()).thenReturn(message);
        Mockito.when(message.getContentRaw()).thenReturn(receivedMessage);

        eventListener.onMessageReceived(messageReceivedEvent);
        Mockito.verify(handler, Mockito.times(0)).handle(messageReceivedEvent);
    }
}
