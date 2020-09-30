package io.github.ditfbot.messagelistener.stirons;

import io.github.ditfbot.config.DITFBotConfig;
import io.github.ditfbot.messagelistener.MessageListener;
import io.github.ditfbot.messagelistener.MessageListenerConfig;
import io.github.ditfbot.util.command.DiscordCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class StironsMessageListener extends MessageListener {
    public StironsMessageListener(DITFBotConfig botConfig) {
        super(botConfig);

        this.messageListenerConfig = new MessageListenerConfig.MessageListenerConfigBuilder()
                .commandName("")
                .botConfig(botConfig)
                .build();
    }

    @Override
    public void handleMessage(DiscordCommand command, MessageReceivedEvent event) {
        //TODO fix arguemnt parsing for DiscordCommand
        //Currently work with event (MessageReceivedEvent) class/object

        //!stirons 0.7 45 18
        //.split(" ")
    }
}
