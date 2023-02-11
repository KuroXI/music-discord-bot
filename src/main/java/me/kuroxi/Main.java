package me.kuroxi;

import me.kuroxi.Event.GuildReady;
import me.kuroxi.Event.GuildVoiceState;
import me.kuroxi.Event.Interaction;
import me.kuroxi.Event.Ready;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Main {
    private final ShardManager shardManager;

    public Main() throws LoginException {
        final String TOKEN = "YOUR TOKEN HERE";
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(TOKEN);

        builder.enableCache(CacheFlag.VOICE_STATE);
        builder.disableCache(CacheFlag.EMOJI, CacheFlag.SCHEDULED_EVENTS,CacheFlag.STICKER, CacheFlag.ACTIVITY, CacheFlag.FORUM_TAGS);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_VOICE_STATES);

        shardManager = builder.build();

        shardManager.addEventListener(
                new Ready(),
                new GuildReady(),
                new Interaction(),
                new GuildVoiceState()
        );
    }

    public static void main(String[] args) {
        try {
            Main main = new Main();
        } catch (LoginException e) {
            System.out.println("Invalid Token");
        }
    }

    public ShardManager getShardManager() {
        return shardManager;
    }
}