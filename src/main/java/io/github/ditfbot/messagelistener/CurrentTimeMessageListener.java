package io.github.ditfbot.messagelistener;

import io.github.ditfbot.config.DITFBotConfig;
import io.github.ditfbot.util.command.DiscordCommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CurrentTimeMessageListener extends MessageListener {
    public CurrentTimeMessageListener(DITFBotConfig botConfig) {
        super(botConfig);

        this.messageListenerConfig = new MessageListenerConfig.MessageListenerConfigBuilder()
                .commandName("currentTime")
                .botConfig(botConfig)
                .build();
    }

    @Override
    public void handleMessage(DiscordCommand command, MessageReceivedEvent event) {
        Message msg = event.getMessage();

        // Get Channel which message originated from
        MessageChannel channel = msg.getChannel();

        // Get current UNIX Epoch time in milliseconds
        Long time = System.currentTimeMillis();

        channel.sendMessage(time.toString())
                // Queue back message to discord (JDA library handles rate-limiting for us)
                .queue();
    }
}
