package matthew.bot.service;

import matthew.bot.repository.ReceivedMessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReceivedEventsServiceTest {
    @Mock
    ReceivedMessageRepository receivedMessageRepository;

    @InjectMocks
    ReceivedEventService receivedEventService;

    @Test
    void saveEventTest(){
        receivedEventService.save(1L);
        Mockito.verify(receivedMessageRepository, Mockito.times(1)).save(Mockito.any());
    }
}
