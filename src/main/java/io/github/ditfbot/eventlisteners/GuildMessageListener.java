package io.github.ditfbot.eventlisteners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.concurrent.Future;

public class GuildMessageListener extends DiscordEventListener<MessageReceivedEvent> {
    @Override
    Future<Void> discordEventHandler(MessageReceivedEvent event, JDA jda) {
        System.out.println(event.getMessage());
        return super.discordEventHandler(event, jda);
    }
}
