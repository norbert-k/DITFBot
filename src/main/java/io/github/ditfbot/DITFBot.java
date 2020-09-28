package io.github.ditfbot;

import io.github.ditfbot.config.DITFBotConfig;

import java.io.IOException;

public class DITFBot {
    public static DITFBotConfig config;

    public static void main(String[] args) {
        // Read BOT Configuration from config.properties (resources folder)
        try {
            // Set config and fail in-case file is corrupted / doesn't exist
            config = DITFBotConfig.getConfig();
            config.printConfig();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.exit(1);
        }

        System.out.println("Starting DITFBot...");
    }
}
