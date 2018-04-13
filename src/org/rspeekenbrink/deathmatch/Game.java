package org.rspeekenbrink.deathmatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.rspeekenbrink.deathmatch.classes.SpawnLocation;
import org.rspeekenbrink.deathmatch.managers.DatabaseManager;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

/**
 * Static Class containing all main game functions and loops
 * It is registered upon plugin load in the Startup class.
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public final class Game {
	private static DatabaseManager db = DatabaseManager.getInstance();
	private static MessageManager msg = MessageManager.getInstance();
	
	public static ArrayList<Player> inGame = new ArrayList<Player>();
	
	/**
	 * Let a player join the game and spawn
	 * 
	 * @param player
	 * @return boolean Succes
	 */
	public static boolean PlayerJoin(Player player) {
		if(inGame.contains(player)) {
			msg.sendErrorMessage("You're already in the game!", player);
			return false;
		}
		
		if(Deathmatch.InMaintenance) {
			msg.sendErrorMessage("The server is in maintenance!", player);
			return false;
		}
		
		
		List<SpawnLocation> gameSpawns = db.getSpawnLocations(SpawnLocation.SpawnType.GAME);
		if(gameSpawns == null || gameSpawns.isEmpty()) {
			msg.sendErrorMessage("No spawns found!", player);
			return false;
		}
		
		inGame.add(player);
		
		Random randomizer = new Random();
		SpawnLocation randomSpawn = gameSpawns.get(randomizer.nextInt(gameSpawns.size()));
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (3*20), 1, true, false)); //3 sec blindness if 20ticks/sec
		player.playSound(randomSpawn, Sound.BLOCK_PORTAL_TRAVEL, 1, 1);
		player.teleport(randomSpawn);
		player.setCanPickupItems(true);
		player.setNoDamageTicks(15 * 20); //Spawn Protection
		player.setFoodLevel(18);
		player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		msg.sendTitleMessage(ChatColor.GREEN + "Good Luck!", "Current Players: " + inGame.size(), player, 20, (4 * 20), 20);
		
		return true;
	}
	
	/**
	 * Run All events needed on game start
	 */
	public static void Start() {
		
	}
	
	/**
	 * Run All events on game stop
	 */
	public static void Stop() {
		
	}
}
