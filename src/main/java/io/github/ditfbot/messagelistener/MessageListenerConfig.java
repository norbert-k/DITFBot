package io.github.ditfbot.messagelistener;

import io.github.ditfbot.config.DITFBotConfig;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.function.Function;

public class MessageListenerConfig {
    public MessageListenerConfig(boolean privateMessages,
                                 boolean guildMessages,
                                 String commandPrefix,
                                 String commandName,
                                 boolean hasArguments,
                                 Function<MessageReceivedEvent, Boolean> customMessageFilter) {
        this.privateMessages = privateMessages;
        this.guildMessages = guildMessages;
        this.commandPrefix = commandPrefix;
        this.commandName = commandName;
        this.hasArguments = hasArguments;
        this.customMessageFilter = customMessageFilter;
    }

    public boolean isPrivateMessages() {
        return privateMessages;
    }

    public boolean isGuildMessages() {
        return guildMessages;
    }

    public String getCommandPrefix() {
        return commandPrefix;
    }

    public String getCommandName() {
        return commandName;
    }

    public boolean isHasArguments() {
        return hasArguments;
    }

    public Function<MessageReceivedEvent, Boolean> getCustomMessageFilter() {
        return customMessageFilter;
    }

    private final boolean privateMessages;
    private final boolean guildMessages;

    private final String commandPrefix;
    private final String commandName;

    private final boolean hasArguments;

    private final Function<MessageReceivedEvent, Boolean> customMessageFilter;

    public static class MessageListenerConfigBuilder {

        private boolean privateMessages = true;
        private boolean guildMessages = true;

        private String commandPrefix;
        private String commandName;

        private boolean hasArguments = false;

        private Function<MessageReceivedEvent, Boolean> customMessageFilter = (message) -> true;

        public MessageListenerConfigBuilder privateMessages(boolean privateMessages) {
            this.privateMessages = privateMessages;
            return this;
        }

        public MessageListenerConfigBuilder guildMessages(boolean guildMessages) {
            this.guildMessages = guildMessages;
            return this;
        }

        public MessageListenerConfigBuilder botConfig(DITFBotConfig config) {
            this.commandPrefix = config.commandPrefix;
            return this;
        }

        public MessageListenerConfigBuilder commandName(String commandName) {
            this.commandName = commandName;
            return this;
        }

        public MessageListenerConfigBuilder hasArguments(boolean hasArguments) {
            this.hasArguments = hasArguments;
            return this;
        }

        public MessageListenerConfigBuilder customMessageFilter(Function<MessageReceivedEvent, Boolean> customMessageFilter) {
            this.customMessageFilter = customMessageFilter;
            return this;
        }

        private void validateBuild() throws IncorrectMessageListenerBuilderArguments {
            if (this.commandPrefix.isEmpty()) {
                throw new IncorrectMessageListenerBuilderArguments("command prefix shouldn't be empty");
            }

            if (this.commandName.isEmpty()) {
                throw new IncorrectMessageListenerBuilderArguments("command name shouldn't be empty");
            }
        }

        public MessageListenerConfig build() throws IncorrectMessageListenerBuilderArguments {
            validateBuild();
            return new MessageListenerConfig(
                    this.privateMessages,
                    this.guildMessages,
                    this.commandPrefix,
                    this.commandName,
                    this.hasArguments,
                    this.customMessageFilter
            );
        }
    }
}
