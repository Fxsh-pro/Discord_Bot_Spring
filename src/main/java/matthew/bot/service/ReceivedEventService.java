package matthew.bot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import matthew.bot.model.ReceivedMessage;
import matthew.bot.repository.ReceivedMessageRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceivedEventService {
    private final ReceivedMessageRepository receivedMessageRepository;

    @Transactional
    public void save(Long userId) {
        receivedMessageRepository.save(ReceivedMessage.builder().userId(userId).build());
    }
}
