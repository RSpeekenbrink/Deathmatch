package org.rspeekenbrink.deathmatch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.rspeekenbrink.deathmatch.Deathmatch;

public class BlockBreak implements Listener {
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(!Deathmatch.InMaintenance) {
			if(!(Deathmatch.InDebug && e.getPlayer().isOp())) {
				e.setCancelled(true);
			}
		}
	}
}
