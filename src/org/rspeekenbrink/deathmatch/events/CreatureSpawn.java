package org.rspeekenbrink.deathmatch.events;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.Plugin;
import org.rspeekenbrink.deathmatch.util.Logger;

/**
 * Creature Spawn Event Listener listens to the Creature Spawn events and handles them. It is registered upon plugin load in the Startup class.
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class CreatureSpawn implements Listener{
	private Plugin plugin;
	private Logger logger;
	private ArrayList<SpawnReason> blockedReasons = new ArrayList<SpawnReason>() {
		private static final long serialVersionUID = 1L;
		{
			add(SpawnReason.NATURAL);
			add(SpawnReason.CHUNK_GEN);
		}
	};
	
	/**
	 * Constructor
	 * 
	 * @param plugin
	 */
	public CreatureSpawn(Plugin plugin) {
		this.plugin = plugin;
		this.logger = new Logger(this.plugin);
	}
	
	/**
	 * This class will block all spawns with the reason in the blockedReasons arrayList
	 * 
	 * @param event CreatureSpawnEvent instance containing all information about the event
	 */
	@EventHandler(priority = EventPriority.HIGH)
	public void onCreatureSpawn(CreatureSpawnEvent event)
	  {
		logger.finest("[CreatureSpawn] Triggered by: " + event.getEntity().getName() + " with spawn reason: " + event.getSpawnReason());
	    if (blockedReasons.contains(event.getSpawnReason())) {
	    	logger.finest("[CreatureSpawn] Blocked spawn of:" + event.getEntity().getName());
	    	event.setCancelled(true);
	    }
	  }
}
