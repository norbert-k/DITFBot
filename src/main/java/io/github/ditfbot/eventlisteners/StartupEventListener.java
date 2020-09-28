package io.github.ditfbot.eventlisteners;

import net.dv8tion.jda.api.events.*;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class StartupEventListener implements EventListener {
    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent) {
            System.out.println("DITFBot is ready");
        } else if (event instanceof ReconnectedEvent) {
            System.out.println("DITFBot reconnected");
        } else if (event instanceof ResumedEvent) {
            System.out.println("DITFBot resumed");
        } else if (event instanceof ShutdownEvent) {
            System.out.println("DITFBot shutting down");
        }
    }
}