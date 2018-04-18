package org.rspeekenbrink.deathmatch.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockSpreadEvent;

public class BlockSpread implements Listener {
	@EventHandler
	public void onBlockSpread(BlockSpreadEvent e) {
		if(e.getSource().getType() == Material.FIRE) {
			e.setCancelled(true);
		}
	}
}
