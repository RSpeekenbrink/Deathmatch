package org.rspeekenbrink.deathmatch.events;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.rspeekenbrink.deathmatch.Deathmatch;
import org.rspeekenbrink.deathmatch.classes.SpawnLocation;
import org.rspeekenbrink.deathmatch.managers.DatabaseManager;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

/**
 * PlayerJoin Event Listeners. This will handle events that are triggered by the player joining the server.
 * It is registered upon plugin load in the Startup class.
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class PlayerJoin implements Listener {
	private MessageManager messageManager = MessageManager.getInstance();
	private DatabaseManager db = DatabaseManager.getInstance();
	
	/**
	 * This function will notify the player if the server is in maintenance mode and set the players standards.
	 * It Will also teleport the player to the set spawn.
	 * 
	 * @param event PlayerJoinEvent instance containing all info about the triggered event
	 */
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
		Player player = event.getPlayer();
		List<SpawnLocation> spawns = db.getSpawnLocations(SpawnLocation.SpawnType.MAIN);
		
		//Check if spawn is set
		if(!spawns.isEmpty()) {
			player.teleport(spawns.get(0));
		}
		else {
			messageManager.OpNotifications.add("No Spawn Set");
		}
		
		event.setJoinMessage("Welcome, " + player.getName() + "!");
		
		if(player.isOp()) {
			messageManager.sendOpNotifications(player);
		}
		
		if(Deathmatch.InMaintenance) {
			if(player.isOp()) {
				event.getPlayer().sendMessage(ChatColor.RED + "Server is in maintenance mode!");
			}
		}
		else {
			player.setGameMode(GameMode.SURVIVAL);
			//TODO: player.setBedSpawnLocation() to spawn
			player.setCanPickupItems(false);
		}
		
    }
}
