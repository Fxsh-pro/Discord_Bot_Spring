package matthew.bot.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import matthew.bot.service.handler.Handler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventListener extends ListenerAdapter {
    private final List<Handler> eventHandlers;

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;
        log.info("Bot has received a message: " + event.getMessage());

        String content = event.getMessage().getContentRaw();
        if (content.trim().equalsIgnoreCase("!ping")) {
            eventHandlers.forEach(handler -> handler.handle(event));
        }
        log.info(event.getMessage() + "has been handled");
    }
}
