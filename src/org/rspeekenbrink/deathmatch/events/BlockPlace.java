package org.rspeekenbrink.deathmatch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.rspeekenbrink.deathmatch.Deathmatch;

public class BlockPlace implements Listener {
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(!Deathmatch.InMaintenance) {
			if(!(Deathmatch.InDebug && e.getPlayer().isOp())) {
				e.setCancelled(true);
			}
		}
	}
}
