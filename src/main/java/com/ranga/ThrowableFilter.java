package com.ranga;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

@Plugin(name = "ThrowableFilter", category = "Core", elementType = "filter", printObject = true)
public final class ThrowableFilter extends AbstractFilter {


    private ThrowableFilter(Result onMatch, Result onMismatch) {
        super(onMatch, onMismatch);
    }

    public Result filter(Logger logger, Level level, Marker marker, String msg, Object[] params) {
        return onMismatch;
    }

    public Result filter(Logger logger, Level level, Marker marker, Object msg, NullPointerException t) {
        return filter(t);
    }

    public Result filter(Logger logger, Level level, Marker marker, Message msg, NullPointerException t) {
        return filter(t);
    }

    @Override
    public Result filter(LogEvent event) {
        return filter(event.getThrown());
    }

    private Result filter(Throwable t) {
        return  t instanceof NullPointerException ? onMatch : onMismatch;
    }

    /**
     * Create a ThrowableFilter.
     * @param match The action to take on a match.
     * @param mismatch The action to take on a mismatch.
     * @return The created ThrowableFilter.
     */
    @PluginFactory
    public static ThrowableFilter createFilter(@PluginAttribute(value = "onMatch", defaultString = "NEUTRAL") Result onMatch,
                                               @PluginAttribute(value = "onMismatch", defaultString = "DENY") Result onMismatch) {
        return new ThrowableFilter(onMatch, onMismatch);
    }
}