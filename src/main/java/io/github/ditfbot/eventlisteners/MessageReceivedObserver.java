package io.github.ditfbot.eventlisteners;

import io.github.ditfbot.config.DITFBotConfig;
import io.github.ditfbot.messagelistener.MessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class MessageReceivedObserver extends DiscordEventListener<MessageReceivedEvent> {
    public MessageReceivedObserver(DITFBotConfig config) {
        super(config);
    }

    private List<MessageListener> messageListeners = new ArrayList<>();

    final public void addMessageListener(MessageListener messageListener) {
        messageListeners.add(messageListener);
    }

    @Override
    final void discordEventHandler(MessageReceivedEvent event, JDA jda) {
        messageListeners.forEach((messageListener -> {
            if (messageListener.messageFilter(event)) {
                messageListener.handleMessage(messageListener.transformMessage(event), event);
            }
        }));
    }
}