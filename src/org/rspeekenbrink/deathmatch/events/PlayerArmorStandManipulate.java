package org.rspeekenbrink.deathmatch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.rspeekenbrink.deathmatch.Deathmatch;

/**
 * PlayerArmorStandManipulate event will avoid players from alterning armor stands
 * It is registered upon plugin load in the Startup class.
 * @see https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/player/PlayerArmorStandManipulateEvent.html
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class PlayerArmorStandManipulate implements Listener {
	@EventHandler
	public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent e) {
		if(!Deathmatch.InMaintenance) {
			e.setCancelled(true);
		}
	}
}
