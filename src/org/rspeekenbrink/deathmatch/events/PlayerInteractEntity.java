package org.rspeekenbrink.deathmatch.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.rspeekenbrink.deathmatch.Deathmatch;

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
