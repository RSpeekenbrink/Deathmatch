package org.rspeekenbrink.deathmatch.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.rspeekenbrink.deathmatch.Deathmatch;
import org.rspeekenbrink.deathmatch.Game;
import org.rspeekenbrink.deathmatch.managers.DatabaseManager;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

public class BlockBreak implements Listener {
	private MessageManager msg = MessageManager.getInstance();
	private DatabaseManager db = DatabaseManager.getInstance();
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(!Deathmatch.InMaintenance) {
			if(!(Deathmatch.InDebug && e.getPlayer().isOp()) || Game.inGame.contains(e.getPlayer())) {
				e.setCancelled(true);
				return;
			}
		}
		
		if(e.getBlock().getType() == Material.CHEST) {
			if(db.isSavedChest(e.getBlock().getLocation())) {
				if(!e.getPlayer().hasPermission("deathmatch.chest.remove")) {
					e.setCancelled(true);
					msg.sendNoPermissionMessage(e.getPlayer());
					return;
				}
				db.deleteChest(e.getBlock().getLocation());
				msg.sendMessage(ChatColor.GREEN + "Saved chest deleted!", e.getPlayer());
			}
		}
	}
}
