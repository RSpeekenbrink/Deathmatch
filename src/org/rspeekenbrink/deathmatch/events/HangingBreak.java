package org.rspeekenbrink.deathmatch.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.rspeekenbrink.deathmatch.Deathmatch;
import org.rspeekenbrink.deathmatch.Game;

/**
 * Hangingbreak event will avoid paintings and itemframes from breaking by players.
 * It is registered upon plugin load in the Startup class.
 * @see https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/hanging/HangingBreakEvent.html
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class HangingBreak implements Listener {
	@EventHandler
	public void onHangingBreak(HangingBreakEvent e) {
		if(e.getCause() == HangingBreakEvent.RemoveCause.ENTITY) {
			HangingBreakByEntityEvent evt = (HangingBreakByEntityEvent) e;
			if(evt.getRemover().getType() == EntityType.PLAYER ) {
				Player player = (Player) evt.getRemover();
				if(!Game.inGame.contains(player)) {
					if(Deathmatch.InMaintenance) {
						return;
					}
				}
			}
		}
		e.setCancelled(true);
	}
}
