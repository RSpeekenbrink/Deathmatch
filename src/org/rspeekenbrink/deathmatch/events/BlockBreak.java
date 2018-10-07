package org.rspeekenbrink.deathmatch.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.rspeekenbrink.deathmatch.Deathmatch;
import org.rspeekenbrink.deathmatch.Game;
import org.rspeekenbrink.deathmatch.handlers.ChestHandler;
import org.rspeekenbrink.deathmatch.managers.DatabaseManager;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

/**
 * BlockBreak, handles BlockBreak event
 * @see https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/block/BlockBreakEvent.html
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class BlockBreak implements Listener {
	private MessageManager msg = MessageManager.getInstance();
	private DatabaseManager db = DatabaseManager.getInstance();
	private ChestHandler ch = ChestHandler.getInstance();
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(!Deathmatch.InMaintenance) {
			if(!(Deathmatch.InDebug && e.getPlayer().isOp()) || Game.inGame.contains(e.getPlayer())) {
				e.setCancelled(true);
				return;
			}
		}
		
		if(e.getBlock().getType() == Material.CHEST) {
			if(ch.isInspecting(e.getPlayer())) {
				e.setCancelled(true);
				
				if(!db.isSavedChest(e.getBlock().getLocation())) {
					msg.sendMessage("This chest is not registered!", e.getPlayer());
				} else {
					msg.sendMessage("This is a " + ch.getChestType(e.getBlock().getLocation()) + " chest!", e.getPlayer());
				}
				
				return;
			}
			
			if(db.isSavedChest(e.getBlock().getLocation())) {
				if(!Deathmatch.InMaintenance) {
					e.setCancelled(true);
					msg.sendErrorMessage("Server needs to be in maintenance mode to break chests!", e.getPlayer());
					return;
				}
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
