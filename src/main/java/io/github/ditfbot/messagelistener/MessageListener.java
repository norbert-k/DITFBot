package io.github.ditfbot.messagelistener;

import io.github.ditfbot.config.DITFBotConfig;
import io.github.ditfbot.util.command.DiscordCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class MessageListener {
    public MessageListener(DITFBotConfig botConfig) {
        this.botConfig = botConfig;
    }

    protected DITFBotConfig botConfig;
    protected MessageListenerConfig messageListenerConfig;

    public boolean messageFilter(MessageReceivedEvent messageReceivedEvent) {
        String rawMessageContent = messageReceivedEvent.getMessage().getContentRaw();
        if (DiscordCommand.checkCommand(this.botConfig.commandPrefix, this.messageListenerConfig.getCommandName(), rawMessageContent)) {
            return this.messageListenerConfig.getCustomMessageFilter().apply(messageReceivedEvent);
        } else {
            return false;
        }
    }

    public DiscordCommand transformMessage(MessageReceivedEvent messageReceivedEvent) {
        String rawMessageContent = messageReceivedEvent.getMessage().getContentRaw();
        return DiscordCommand.fromRawMessage(this.botConfig.commandPrefix, this.messageListenerConfig.getCommandName(), rawMessageContent);
    }

    public void handleMessage(DiscordCommand command, MessageReceivedEvent event) {
        
    }
}
