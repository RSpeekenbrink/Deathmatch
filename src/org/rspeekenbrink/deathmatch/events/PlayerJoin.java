package org.rspeekenbrink.deathmatch.events;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.rspeekenbrink.deathmatch.Deathmatch;
import org.rspeekenbrink.deathmatch.classes.SpawnLocation;
import org.rspeekenbrink.deathmatch.managers.DatabaseManager;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

/**
 * PlayerJoin Event Listeners. This will handle events that are triggered by the player joining the server.
 * It is registered upon plugin load in the Startup class.
 * @see https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/player/PlayerJoinEvent.html
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class PlayerJoin implements Listener {
	private MessageManager msg = MessageManager.getInstance();
	private Plugin plugin;
	private DatabaseManager db;
	
	/**
	 * Constructor
	 */
	public PlayerJoin(Plugin plugin) {
		this.plugin = plugin;
		this.db = DatabaseManager.getInstance(this.plugin);
	}
	
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
		List<SpawnLocation> mainSpawnList = db.getSpawnLocations(SpawnLocation.SpawnType.MAIN);
		
		//Check if spawn is set
		if(mainSpawnList != null) {
			SpawnLocation mainSpawn = mainSpawnList.get(0);
			player.teleport(mainSpawn);
			player.setBedSpawnLocation(mainSpawn, true);
		}
		else {
			msg.OpNotifications.add("No server spawn location has been set yet! \nUse '/dm spawn add main' to set one!");
		}
		
		event.setJoinMessage("Welcome, " + player.getName() + "!");
		
		if(Deathmatch.InMaintenance) {
			if(player.hasPermission("deathmatch.join.maintenance")) {
				event.getPlayer().sendMessage(ChatColor.DARK_PURPLE + "Server is in maintenance mode!");
			} else {
				player.kickPlayer("�l�bServer is in maintenance\n�r�3Please join back later!");
			}
		}
		if(Deathmatch.InDebug) {
			msg.OpNotifications.add(ChatColor.RED + "Server is in DEBUG mode!");
		}
			
		player.setGameMode(GameMode.SURVIVAL);
		player.setCanPickupItems(false);
		
		
		if(player.isOp()) {
			msg.sendOpNotifications(player);
		}
		
    }
}
