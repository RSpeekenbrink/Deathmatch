package org.rspeekenbrink.deathmatch;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.*;
import org.rspeekenbrink.deathmatch.events.*;
import org.rspeekenbrink.deathmatch.handlers.CommandHandler;
import org.rspeekenbrink.deathmatch.managers.*;
import org.rspeekenbrink.deathmatch.util.Logger;

/**
 * Deathmatch Class is the starting class of the plugin. This class contains the functions that will load upon enabling the plugin.
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class Deathmatch extends JavaPlugin {
	private Plugin plugin = this;
	private Logger logger = new Logger(plugin);
	
	public static boolean InMaintenance = false;
	public static boolean InDebug = false;
	
	/**
	 * This function will run when the plugin is started. In the function it will make a new instance of the startup runnable 
	 * class which will load the rest of the plugin.
	 *
	 * @return      Nothing
	 * @see 		Startup
	 */
	@Override
    public void onEnable() {
		//Make sure all worlds are loaded before starting the game
		getServer().getScheduler().scheduleSyncDelayedTask(this, new Startup(), 10);
    }
    
	/**
	 * This function will run when the plugin is shutdown.
	 *
	 * @return      Nothing
	 */
    @Override
    public void onDisable() {
    	logger.info("Stopping Game..");
    	Game.Stop();
    	logger.info("Disabled!");
    }
    
    /**
     * This is the startup runnable class. When this class is created on starting the plugin it will load all other required items
     * and it will start the game loop. It will also take care of registering all events etc.
     * 
     * @author 		Fisher
     * @version 	1.0
     * @since       1.0
     *
     */
    class Startup implements Runnable {

		@Override
		public void run() {
			logger.info("Setting up data..");
			SettingsManager.getInstance().setup(plugin);
			
			if(getConfig().getBoolean("debug")) {
				logger.setLogLevel(Level.parse(getConfig().getString("debug-level")));
				logger.info("In debug mode with debug level: " + logger.getLogLevel());
				Deathmatch.InDebug = true;
			}
			
			logger.fine("Setting up DB connection..");
			DatabaseManager db = DatabaseManager.getInstance(plugin);
			db.load();
			
			InMaintenance = getConfig().getBoolean("maintenance");
			logger.fine("Server in maintenance: " + InMaintenance);
			
			logger.fine("Setting up world settings..");
			for(World worlds: Bukkit.getWorlds()) {
				if(InMaintenance)
				{
					worlds.setAutoSave(true); 
					logger.finer("World Saving for " + worlds.getName() + " Enabled!");
				}
				else
				{
				    worlds.setAutoSave(false); 
				    logger.finer("World Saving for " + worlds.getName() + " Disabled!");
				}
				
				logger.finer("Setting up world settings for " + worlds.getName() + "..");
				worlds.setPVP(true);
				worlds.setAnimalSpawnLimit(0);
				worlds.setMonsterSpawnLimit(0);
				worlds.setWaterAnimalSpawnLimit(0);
				worlds.setAmbientSpawnLimit(0);
				worlds.setDifficulty(Difficulty.HARD);
			}
			
			logger.fine("Setting up events..");
			PluginManager pm = getServer().getPluginManager();
			pm.registerEvents(new CreatureSpawn(plugin), plugin);
			pm.registerEvents(new EntityDamage(), plugin);
			pm.registerEvents(new EntityDeath(), plugin);
			pm.registerEvents(new PlayerJoin(plugin), plugin);
			pm.registerEvents(new PlayerQuit(), plugin);
			
			logger.fine("Setting up commands..");
			setupCommands();
			
			logger.info("Starting Game..");
			Game.Start();
			
			logger.info("Enabled!");
		}
    	
		private void setupCommands() {
			getCommand("deathmatch").setExecutor(new CommandHandler(plugin));
		}
    }
}


