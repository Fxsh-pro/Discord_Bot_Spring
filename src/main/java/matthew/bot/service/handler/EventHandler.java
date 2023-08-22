package matthew.bot.service.handler;

import lombok.RequiredArgsConstructor;
import matthew.bot.service.ReceivedEventService;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventHandler implements Handler {
    private final ReceivedEventService receivedEventService;

    @Async
    public void handle(MessageReceivedEvent event) {
        receivedEventService.save(event.getAuthor().getIdLong());
        MessageChannel channel = event.getChannel();
        channel.sendMessage("Pong! " + event.getAuthor().getName()).queue();
    }
}
