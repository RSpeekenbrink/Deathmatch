package org.rspeekenbrink.deathmatch.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.rspeekenbrink.deathmatch.Game;
import org.rspeekenbrink.deathmatch.handlers.ChestHandler;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

public class PlayerInteract implements Listener {
	private Plugin plugin;
	
	public PlayerInteract(Plugin plugin) {
		this.plugin = plugin;
	}
	
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		//Prevent trampling farmland
		if(e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.SOIL) {
	        e.setCancelled(true);
	        return;
		}
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getState() instanceof Sign) {
				Sign sign = (Sign) e.getClickedBlock().getState();
				if(sign.getLine(0).equals(ChatColor.DARK_GRAY + "[DeathMatch]")) {
					//Handle DM Signs
					if(sign.getLine(1).equals(ChatColor.DARK_AQUA + "Join")) {
						if(!e.getPlayer().hasPermission("deathmatch.join")) {
							MessageManager.getInstance().sendNoPermissionMessage(e.getPlayer());
							return;
						}
						Game.PlayerJoin(e.getPlayer());
					}
				}
			}
			
			if(e.getClickedBlock().getType() == Material.CHEST) {
				if(Game.inGame.contains(e.getPlayer())) {
					ChestHandler chestHandler = ChestHandler.getInstance();
					if(chestHandler.isGameChest(e.getClickedBlock().getLocation())) {
						chestHandler.dropItem(e.getClickedBlock().getLocation(), plugin);
					}
				}
				else {
					e.setCancelled(true);
				}
			}
		}
	}
}
