package io.github.ditfbot.discordbot;

import io.github.ditfbot.config.DITFBotConfig;
import io.github.ditfbot.eventlisteners.GuildMessageListener;
import io.github.ditfbot.eventlisteners.MessageReceivedObserver;
import io.github.ditfbot.eventlisteners.StartupEventListener;
import io.github.ditfbot.messagelistener.CurrentTimeMessageListener;
import io.github.ditfbot.messagelistener.PingPongMessageListener;
import io.github.ditfbot.messagelistener.stirons.StironsMessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class DITFDiscordBot {
    private JDA jda;

    private MessageReceivedObserver messageReceivedObserver;

    public DITFDiscordBot(DITFBotConfig ditfBotConfig) throws LoginException {
        messageReceivedObserver = new MessageReceivedObserver(ditfBotConfig);

        messageReceivedObserver.addMessageListener(new PingPongMessageListener(ditfBotConfig));
        messageReceivedObserver.addMessageListener(new CurrentTimeMessageListener(ditfBotConfig));
        messageReceivedObserver.addMessageListener(new StironsMessageListener(ditfBotConfig));

        jda = JDABuilder.createDefault(ditfBotConfig.discordToken)
                .addEventListeners(new StartupEventListener())
                .addEventListeners(new GuildMessageListener(ditfBotConfig))
                .addEventListeners(messageReceivedObserver)
                .build();
    }
}
