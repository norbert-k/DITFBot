package io.github.ditfbot;

import io.github.ditfbot.discordbot.DITFDiscordBot;
import io.github.ditfbot.config.DITFBotConfig;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class DITFBot {
    public static final String logo;

    public static DITFDiscordBot discordBot;

    static {
        logo = "  _____ _____ _______ ______ ____        _   \n" +
                " |  __ \\_   _|__   __|  ____|  _ \\      | |  \n" +
                " | |  | || |    | |  | |__  | |_) | ___ | |_ \n" +
                " | |  | || |    | |  |  __| |  _ < / _ \\| __|\n" +
                " | |__| || |_   | |  | |    | |_) | (_) | |_ \n" +
                " |_____/_____|  |_|  |_|    |____/ \\___/ \\__|\n";
    }

    public static DITFBotConfig config;

    public static void main(String[] args) {

        System.out.println(logo);
        // Read BOT Configuration from config.properties (resources folder)
        try {
            // Set config and fail in-case file is corrupted / doesn't exist
            config = DITFBotConfig.getConfig();
            config.printConfig();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.exit(1);
        }

        try {
            discordBot = new DITFDiscordBot(config);
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Starting DITFBot...");
    }
}
