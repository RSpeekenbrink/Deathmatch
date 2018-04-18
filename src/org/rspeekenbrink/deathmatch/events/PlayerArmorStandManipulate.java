package org.rspeekenbrink.deathmatch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.rspeekenbrink.deathmatch.Deathmatch;

public class PlayerArmorStandManipulate implements Listener {
	@EventHandler
	public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent e) {
		if(!Deathmatch.InMaintenance) {
			e.setCancelled(true);
		}
	}
}
