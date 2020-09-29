package io.github.ditfbot.eventlisteners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class PingPongEventListener extends DiscordEventListener<MessageReceivedEvent> {
    @Override
    boolean eventFilter(MessageReceivedEvent event) {
        return event.getMessage().getContentRaw().equals("!ping");
    }

    @Override
    Future<Void> discordEventHandler(MessageReceivedEvent event, JDA jda) {
        Message msg = event.getMessage();
        MessageChannel channel = msg.getChannel();

        long time = System.currentTimeMillis();
        channel.sendMessage("pong!")
                .queue(response -> {
                    response.editMessageFormat("pong!: %d ms", System.currentTimeMillis() - time).queue();
                });
        return new CompletableFuture<>();
    }
}
