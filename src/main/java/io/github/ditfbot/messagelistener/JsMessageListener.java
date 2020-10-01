package io.github.ditfbot.messagelistener;

import io.github.ditfbot.config.DITFBotConfig;
import io.github.ditfbot.util.command.DiscordCommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class JsMessageListener extends MessageListener {
    public JsMessageListener(DITFBotConfig botConfig) {
        super(botConfig);

        this.messageListenerConfig = new MessageListenerConfig.MessageListenerConfigBuilder()
                .botConfig(botConfig)
                .hasArguments(true)
                .commandName("js")
                .build();
    }

    @Override
    public void handleMessage(DiscordCommand command, MessageReceivedEvent event) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("rhino");

        Message msg = event.getMessage();

        // Get Channel which message originated from
        MessageChannel channel = msg.getChannel();

        String javascript = "";

        if (command.hasArguments) {
            javascript = String.join("", command.arguments);
        }

        try {
            String output = engine.eval(javascript).toString();
            channel.sendMessage(output)
                    // Queue back message to discord (JDA library handles rate-limiting for us)
                    .queue();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
