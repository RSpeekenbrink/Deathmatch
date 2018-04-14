package org.rspeekenbrink.deathmatch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.rspeekenbrink.deathmatch.Game;

/**
 * PlayerQuit Event Listeners. This will handle events that are triggered by the player leaving the server.
 * It is registered upon plugin load in the Startup class.
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class PlayerQuit implements Listener {
	/**
	 * Resets the player and handles fair leaving etc.
	 * 
	 * @param event PlayerQuitEvent instance with all info about the event
	 */
	@EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
		Game.PlayerLeave(event.getPlayer());
		event.setQuitMessage(event.getPlayer().getName() + " left the game!");
    }
}
