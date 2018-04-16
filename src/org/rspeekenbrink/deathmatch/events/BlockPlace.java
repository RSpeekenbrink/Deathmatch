package org.rspeekenbrink.deathmatch.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.rspeekenbrink.deathmatch.Deathmatch;
import org.rspeekenbrink.deathmatch.Game;
import org.rspeekenbrink.deathmatch.handlers.ChestHandler;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

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
		
		if(e.getBlockPlaced().getType() == Material.CHEST) {
			if(chestHandler.isPlaceAbleChest(e.getItemInHand()) && e.getPlayer().hasPermission("deathmatch.chest.place")) {
				chestHandler.placeChest(e.getItemInHand(), e.getBlockPlaced().getLocation());
				msg.sendMessage("Chest saved!", e.getPlayer());
			}
		}
	}
}
