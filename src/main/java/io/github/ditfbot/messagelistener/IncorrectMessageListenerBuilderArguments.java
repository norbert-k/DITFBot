package io.github.ditfbot.messagelistener;

public class IncorrectMessageListenerBuilderArguments
        extends RuntimeException {
    public IncorrectMessageListenerBuilderArguments(String errorMessage) {
        super(errorMessage);
    }
}
