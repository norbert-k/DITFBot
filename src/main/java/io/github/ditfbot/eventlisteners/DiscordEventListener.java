package io.github.ditfbot.eventlisteners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.ParameterizedType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Handles incoming Discord events that are specified by T type (T extends GenericEvent), and
 * additional typeFilter method to fine tune incoming events.
 */
public abstract class DiscordEventListener<T extends GenericEvent> extends ListenerAdapter {
    // Save T type to bypass JVM type erasure
    private Class<T> clazz;

    // Get Parameterized (initialized non-null) Class<T>
    public Class<T> getParameterizedClass() {
        if (clazz == null) {
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            clazz = (Class<T>) pt.getActualTypeArguments()[0];
        }
        return clazz;
    }

    @Override
    public void onGenericEvent(@NotNull GenericEvent event) {
        // Check if event corresponds to specified (T extend GenericEvent) type
        if (typeFilter(event)) {
            T castEvent = clazz.cast(event);
            // Extra event filter
            if (eventFilter(castEvent)) {
                discordEventHandler(castEvent, castEvent.getJDA());
            }
        }
    }

    /**
     * Filters incoming events by their type (only allows T types through)
     *
     * @param event generic discord event (GenericEvent)
     * @return non-null and equal to T type only boolean check
     * @see boolean
     */
    boolean typeFilter(GenericEvent event) {
        return event != null && event.getClass() == getParameterizedClass();
    }

    /**
     * Extra filter to fine tune incoming events
     *
     * @param  event  T event (cast GenericEvent to T type)
     * @return      user specified boolean check (returns true by default)
     * @see         boolean
     */
    boolean eventFilter(T event) {
        return true;
    }

    // Event handler
    Future<Void> discordEventHandler(T event, JDA jda) {
        return new CompletableFuture<>();
    }
}
