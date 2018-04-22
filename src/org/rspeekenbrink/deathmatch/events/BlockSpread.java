package org.rspeekenbrink.deathmatch.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockSpreadEvent;

/**
 * BlockSpread, Handles BlockSpread event (fire,mushrooms)
 * @see https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/block/BlockSpreadEvent.html
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class BlockSpread implements Listener {
	@EventHandler
	public void onBlockSpread(BlockSpreadEvent e) {
		if(e.getSource().getType() == Material.FIRE) {
			e.setCancelled(true);
		}
	}
}
