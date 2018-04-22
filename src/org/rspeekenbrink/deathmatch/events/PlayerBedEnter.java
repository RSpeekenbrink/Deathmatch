package org.rspeekenbrink.deathmatch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

/**
 * PlayerBedEnter Event will avoid spawn altering or time altering trough sleeping
 * It is registered upon plugin load in the Startup class.
 * @see https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/player/PlayerBedEnterEvent.html
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class PlayerBedEnter implements Listener {
	MessageManager msg = MessageManager.getInstance();
	
	@EventHandler
	public void onPlayerBedEnterEvent(PlayerBedEnterEvent e) {
		msg.sendErrorMessage("You can't sleep now!", e.getPlayer());
		e.setCancelled(true);
	}
}
