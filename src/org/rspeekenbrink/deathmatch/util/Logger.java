package org.rspeekenbrink.deathmatch.util;

import java.util.logging.Level;

import org.bukkit.plugin.Plugin;

/**
 * Logger class used to be able to log on different debugging levels. original from: https://gist.github.com/MarkLalor/d21ac9b3decd65c88a9171d5d6aeb858
 * Edited to make it compatible with the plugin
 * 
 * @author 		Mark Lalor
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class Logger {
	private java.util.logging.Logger logger;
	private Level level;
	
	public Logger(Plugin plugin) {
		logger = plugin.getLogger();
		level = Level.INFO;
	}
	
    public java.util.logging.Logger getLogger()
    {
        return logger;
    }
    
    public Level getLogLevel()
    {
        return level;
    }
    
    public void setLogLevel(Level level)
    {
        this.level = level;
    }
    
    public void setLogLevel(String level)
    {
        setLogLevel(Level.parse(level.toUpperCase()));
    }
    
    private static String prefix(Level level, String msg)
    {
        return "[" + level.getName() + "] " + msg;
    }
    
    private boolean shouldLog(Level level)
    {
        return level.intValue() >= this.level.intValue();
    }
    
    public void log(Level level, String message, Object param1)
    {
        if(shouldLog(level))
            logger.log(Level.INFO, prefix(level, message), param1);
    }
    
    public void log(Level level, String message, Object... params)
    {
        if(shouldLog(level))
            logger.log(Level.INFO, prefix(level, message), params);
    }
    
    public void log(Level level, String message)
    {
        if(shouldLog(level))
            logger.log(Level.INFO, prefix(level, message));
    }
    
    public void fine(String message)
    {
        log(Level.FINE, message);
    }
    
    public void fine(String message, Object param1)
    {
        log(Level.FINE, message, param1);
    }
    
    public void fine(String message, Object... params)
    {
        log(Level.FINE, message, params);
    }
    
    public void finer(String message)
    {
        log(Level.FINER, message);
    }
    
    public void finer(String message, Object param1)
    {
        log(Level.FINER, message, param1);
    }
    
    public void finer(String message, Object... params)
    {
        log(Level.FINER, message, params);
    }
    
    public void finest(String message)
    {
        log(Level.FINEST, message);
    }
    
    public void finest(String message, Object param1)
    {
        log(Level.FINEST, message, param1);
    }
    
    public void finest(String message, Object... params)
    {
        log(Level.FINEST, message, params);
    }
    
    public void info(String message)
    {
        log(Level.INFO, message);
    }
    
    public void info(String message, Object param1)
    {
        log(Level.INFO, message, param1);
    }
    
    public void info(String message, Object... params)
    {
        log(Level.INFO, message, params);
    }
    
    public void warning(String message)
    {
        log(Level.WARNING, message);
    }
    
    public void warning(String message, Object param1)
    {
        log(Level.WARNING, message, param1);
    }
    
    public void warning(String message, Object... params)
    {
        log(Level.WARNING, message, params);
    }
    
    public void severe(String message)
    {
        log(Level.SEVERE, message);
    }
    
    public void severe(String message, Object param1)
    {
        log(Level.SEVERE, message, param1);
    }
    
    public void severe(String message, Object... params)
    {
        log(Level.SEVERE, message, params);
    }
    
    public void warning(String message, Exception err)
    {
        warning(message);
        if(err != null)
            warning(err.getMessage());
    }
    
    public void severe(String message, Exception err)
    {
        severe(message);
        if(err != null)
            severe(err.getMessage());
    }

}
