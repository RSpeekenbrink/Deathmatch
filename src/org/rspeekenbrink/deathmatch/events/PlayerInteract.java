package org.rspeekenbrink.deathmatch.events;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.rspeekenbrink.deathmatch.Game;

public class PlayerInteract implements Listener {
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getState() instanceof Sign) {
				Sign sign = (Sign) e.getClickedBlock().getState();
				if(sign.getLine(0).equals(ChatColor.DARK_GRAY + "[DeathMatch]")) {
					//Handle DM Signs
					if(sign.getLine(1).equals(ChatColor.DARK_AQUA + "Join")) {
						Game.PlayerJoin(e.getPlayer());
					}
				}
			}
		}
	}
}
