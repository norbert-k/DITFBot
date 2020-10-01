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

    private void sendNormalPong(long time, DiscordCommand command, Message msg, MessageChannel channel) {
        String message = "pong!";

        if (command.hasArguments) {
            message += " " + command.arguments[0];
        }

        String finalMessage = message;
        channel.sendMessage(message)
                // Queue back message to discord (JDA library handles rate-limiting for us)
                .queue(response -> {
                    // When successful edit message to include response time
                    response.editMessageFormat(finalMessage + ": %d ms, requester: [%s]",
                            System.currentTimeMillis() - time,
                            msg.getAuthor().getName()).queue();
                });
    }

    @Override
    public void handleMessage(DiscordCommand command, MessageReceivedEvent event) {
        Message msg = event.getMessage();

        // Get Channel which message originated from
        MessageChannel channel = msg.getChannel();

        // Get current UNIX Epoch time in milliseconds
        long time = System.currentTimeMillis();

        sendNormalPong(time, command, msg, channel);
    }
}
