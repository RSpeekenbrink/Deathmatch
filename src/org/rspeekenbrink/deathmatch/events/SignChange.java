package org.rspeekenbrink.deathmatch.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChange implements Listener {
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if(e.getPlayer().hasPermission("deathmatch.place.sign")) {
			if(e.getLine(0).equalsIgnoreCase("[dmjoin]")) {
				e.setLine(0, ChatColor.DARK_GRAY + "[DeathMatch]");
				e.setLine(1, ChatColor.DARK_AQUA + "Join");
				e.setLine(2, "§r§fClick here to join");
				e.setLine(3, "§r§fDeathmatch!");
			}
		}
	}	

}
