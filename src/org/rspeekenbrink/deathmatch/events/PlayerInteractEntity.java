package org.rspeekenbrink.deathmatch.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.rspeekenbrink.deathmatch.Deathmatch;

/**
 * PlayerInteractEntity Event will handle all left an right mouse button interactions on entities
 * It will overrule PlayerArmorStandManipulate if dealing with armor stands
 * It is registered upon plugin load in the Startup class.
 * @see https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/player/PlayerInteractEntityEvent.html
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class PlayerInteractEntity implements Listener {
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		if(e.getRightClicked().getType().equals(EntityType.ITEM_FRAME)) {
			if(!Deathmatch.InMaintenance) {
				e.setCancelled(true);
				return;
			}
		}
	}
}
