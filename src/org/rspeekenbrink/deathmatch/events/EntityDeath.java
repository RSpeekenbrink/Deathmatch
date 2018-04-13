package org.rspeekenbrink.deathmatch.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * EntityDeath Event will handle all points etc.
 * It is registered upon plugin load in the Startup class.
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class EntityDeath implements Listener {
	
	@EventHandler
	public void onPlayerDeath(EntityDeathEvent e) 
	{
		LivingEntity entity = e.getEntity();
		
		if(entity.getType() == EntityType.PLAYER) {
			//player died *sad*
			Player killed = (Player) entity;
			//Do Stuff
		}
	}
	
}