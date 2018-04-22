package org.rspeekenbrink.deathmatch.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

/**
 * SignChange Event will handle plugin sign creation
 * It is registered upon plugin load in the Startup class.
 * @see https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/block/SignChangeEvent.html
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
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
