package matthew.bot.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import matthew.bot.listeners.EventListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscordBotConfig {
    private ShardManager shardManager;
    private final EventListener eventListener;
    @Value("${bot.token}")
    private String botToken;

    @PostConstruct
    public void init() {
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(botToken, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening("тебя"));
        shardManager = builder.build();

        shardManager.addEventListener(eventListener);
    }
}
