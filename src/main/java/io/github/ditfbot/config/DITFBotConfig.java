package io.github.ditfbot.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DITFBotConfig {
    public final String discordToken;

    public DITFBotConfig(String discordToken) {
        this.discordToken = discordToken;
    }

    public void printConfig() {
        System.out.println("---DITFBot configuration start ---");
        System.out.println("Token: " + this.discordToken);
        System.out.println("---DITFBot configuration end ---");
    }

    public static DITFBotConfig getConfig() throws IOException {
        InputStream input = DITFBotConfig.class.getClassLoader().getResourceAsStream("config.properties");

        Properties prop = new Properties();

        if (input == null) {
            System.out.println("Sorry, unable to find config.properties");
            return null;
        }

        //load a properties file from class path, inside static method
        prop.load(input);

        return new DITFBotConfig(prop.getProperty("DiscordToken"));
    }
}
