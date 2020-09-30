package io.github.ditfbot.util.command;

public class DiscordCommand {
    public DiscordCommand(String commandName, String[] arguments) {
        this.commandName = commandName;
        this.arguments = arguments;

        if (this.arguments == null) {
            this.hasArguments = false;
            this.hasMultipleArguments = false;
        } else {
            if (this.arguments.length >= 1) {
                this.hasArguments = true;
                this.hasMultipleArguments = this.arguments.length > 1;
            } else {
                this.hasArguments = false;
                this.hasMultipleArguments = false;
            }
        }
    }

    public static final String argumentDivider = ",";

    public final String commandName;
    public final String[] arguments;

    public final boolean hasArguments;
    public final boolean hasMultipleArguments;

    private static String removePrefix(String value, String prefix) {
        if (value != null && prefix != null && value.startsWith(prefix)) {
            return value.substring(prefix.length());
        }
        return value;
    }


    private static DiscordCommand extractMessageArguments(String message) {
        String[] splitMessage = message.split(" ", 2);
        String command = splitMessage[0];
        if (splitMessage.length > 1) {
            String[] arguments = splitMessage[1].split(argumentDivider);
            return new DiscordCommand(command, arguments);
        } else {
            return new DiscordCommand(command, null);
        }
    }

    public static boolean checkCommand(String commandPrefix, String commandName, String message) {
        if (message.startsWith(commandPrefix)) {
            return removePrefix(message, commandPrefix).equals(commandName);
        } else {
            return false;
        }
    }

    public static boolean checkRawMessage(String commandPrefix, String commandName, String message) {
        return checkCommand(commandPrefix, commandName, message);
    }

    public static DiscordCommand fromRawMessage(String commandPrefix, String commandName, String message) {
        if (checkCommand(commandPrefix, commandName, message)) {
            String messageWithoutPrefix = removePrefix(message, commandPrefix);
            return extractMessageArguments(messageWithoutPrefix);
        } else {
            throw new IncorrectDiscordCommandException("Invalid discord command");
        }
    }
}

