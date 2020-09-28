package io.github.ditfbot.discordbot;

import io.github.ditfbot.config.DITFBotConfig;
import io.github.ditfbot.eventlisteners.StartupEventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class DITFDiscordBot {
    private JDA jda;

    public DITFDiscordBot(DITFBotConfig ditfBotConfig) throws LoginException {
        jda = JDABuilder.createDefault(ditfBotConfig.discordToken)
                .addEventListeners(new StartupEventListener())
                .build();
    }
}
