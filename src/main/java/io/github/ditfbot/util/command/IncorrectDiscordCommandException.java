package io.github.ditfbot.util.command;

public class IncorrectDiscordCommandException
        extends RuntimeException {
    public IncorrectDiscordCommandException(String errorMessage) {
        super(errorMessage);
    }
}
