package matthew.bot.service;

import matthew.bot.service.handler.EventHandler;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EventHandlerTest {
    @Mock
    ReceivedEventService receivedEventService;
    @Mock
    MessageReceivedEvent messageReceivedEvent;
    @Mock
    User user;
    @Mock
    MessageChannelUnion messageChannel;
    @Mock
    MessageCreateAction mockMessageAction;

    @InjectMocks
    EventHandler eventHandler;

    @Test
    void handle() {
        long userId = 1;
        Mockito.when(messageReceivedEvent.getAuthor()).thenReturn(user);
        Mockito.when(user.getIdLong()).thenReturn(userId);
        Mockito.when(messageReceivedEvent.getChannel()).thenReturn(messageChannel);
        Mockito.when(messageChannel.sendMessage(Mockito.anyString())).thenReturn(mockMessageAction);

        eventHandler.handle(messageReceivedEvent);
        Mockito.verify(receivedEventService, Mockito.times(1)).save(userId);
    }

}
