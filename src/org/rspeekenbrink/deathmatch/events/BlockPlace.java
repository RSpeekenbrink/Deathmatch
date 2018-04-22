package org.rspeekenbrink.deathmatch.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.rspeekenbrink.deathmatch.Deathmatch;
import org.rspeekenbrink.deathmatch.Game;
import org.rspeekenbrink.deathmatch.handlers.ChestHandler;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

/**
 * BlockPlace, handles BlockPlace event
 * @see https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/event/block/BlockBreakEvent.html
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class BlockPlace implements Listener {
	ChestHandler chestHandler = ChestHandler.getInstance();
	MessageManager msg = MessageManager.getInstance();
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(!Deathmatch.InMaintenance) {
			if(!(Deathmatch.InDebug && e.getPlayer().isOp()) || Game.inGame.contains(e.getPlayer())) {
				e.setCancelled(true);
				return;
			}
		}
		
		if(e.getItemInHand().getType() == Material.CHEST && e.getPlayer().hasPermission("deathmatch.chest.place")) {
			if(chestHandler.isPlaceAbleChest(e.getItemInHand())) {
				chestHandler.placeChest(e.getItemInHand(), e.getBlockPlaced().getLocation());
				msg.sendMessage("Chest saved!", e.getPlayer());
			}
		}
	}
}
