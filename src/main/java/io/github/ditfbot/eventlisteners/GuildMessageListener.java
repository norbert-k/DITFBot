package io.github.ditfbot.eventlisteners;

import io.github.ditfbot.config.DITFBotConfig;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class GuildMessageListener extends DiscordEventListener<MessageReceivedEvent> {
    public GuildMessageListener(DITFBotConfig config) {
        super(config);
    }

    @Override
    void discordEventHandler(MessageReceivedEvent event, JDA jda) {
        System.out.println(event.getMessage());
    }
}
