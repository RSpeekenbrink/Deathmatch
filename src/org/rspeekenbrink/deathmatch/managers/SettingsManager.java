package org.rspeekenbrink.deathmatch.managers;

import org.bukkit.plugin.Plugin;

/**
 * Settings manager is used to manage the config file.  This Manager is singleton and will be required to
 * be defined using the getInstace() function.
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class SettingsManager {
	private static SettingsManager instance;
	private Plugin p;
	
	/**
	 * Constructor
	 */
	public SettingsManager() {
		
	}
	
	/**
	 * Requests the instance of the SettingsManager Class, if this does not exist create the instance.
	 * 
	 * @return SettingsManager
	 */
	public static SettingsManager getInstance() {
		if(instance == null) {
			instance = new SettingsManager();
		}
		return instance;
	}
	
	/**
	 * Function that is called in the Startup Class on plugin start to load the config files or else create them if they
	 * do not exist.
	 * 
	 * @param p Plugin
	 */
	public void setup(Plugin p) {
		this.p = p;
		
		p.getConfig().options().copyDefaults(true);
		p.saveDefaultConfig();
	}
	
	/**
	 * Function to reload the config files
	 */
	public void reload() {
		p.reloadConfig();
	}
}
