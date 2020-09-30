package io.github.ditfbot.messagelistener;

import io.github.ditfbot.config.DITFBotConfig;
import io.github.ditfbot.util.command.DiscordCommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PingPongMessageListener extends MessageListener {
    public PingPongMessageListener(DITFBotConfig botConfig) {
        super(botConfig);

        this.messageListenerConfig = new MessageListenerConfig.MessageListenerConfigBuilder()
                .commandName("ping")
                .botConfig(botConfig)
                .build();
    }

    private void sendNormalPong(long time, MessageChannel channel) {
        channel.sendMessage("pong!")
                // Queue back message to discord (JDA library handles rate-limiting for us)
                .queue(response -> {
                    // When successful edit message to include response time
                    response.editMessageFormat("pong!: %d ms", System.currentTimeMillis() - time).queue();
                });
    }

    private void sendArgumentPong(long time, MessageChannel channel, String argument) {
        channel.sendMessage("pong! " + argument)
                // Queue back message to discord (JDA library handles rate-limiting for us)
                .queue(response -> {
                    // When successful edit message to include response time
                    response.editMessageFormat("pong!: %d ms", System.currentTimeMillis() - time).queue();
                });
    }

    @Override
    public void handleMessage(DiscordCommand command, MessageReceivedEvent event) {
        Message msg = event.getMessage();

        // Get Channel which message originated from
        MessageChannel channel = msg.getChannel();

        // Get current UNIX Epoch time in milliseconds
        long time = System.currentTimeMillis();

        if (command.hasArguments) {
            sendArgumentPong(time, channel, command.arguments[0]);
        } else {
            sendNormalPong(time, channel);
        }
    }
}
