package org.rspeekenbrink.deathmatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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
		
		player.setGameMode(GameMode.SURVIVAL);
		player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (3*20), 1, true, false)); //3 sec blindness if 20ticks/sec
		player.playSound(randomSpawn, Sound.BLOCK_PORTAL_TRAVEL, 1, 1);
		player.teleport(randomSpawn);
		player.setCanPickupItems(true);
		player.setNoDamageTicks(8 * 20); //Spawn Protection
		player.setFoodLevel(20);
		player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		msg.sendTitleMessage(ChatColor.GREEN + "Good Luck!", "Current Players: " + inGame.size(), player, 20, (4 * 20), 20);
		
		/*************TEMP FOR TESTING*********************/
		ItemStack item = new ItemStack(Material.BOW, 1);
		item.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		player.getInventory().addItem(item);
		
		item = new ItemStack(Material.ARROW, 1);
		player.getInventory().addItem(item);
		
		item = new ItemStack(Material.DIAMOND_SWORD, 1);
		item.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		player.getInventory().addItem(item);
		
		item = new ItemStack(Material.COOKED_MUTTON, 64);
		player.getInventory().addItem(item);
		/*************************************************/
		
		return true;
	}
	
	/**
	 * Let player leave and remove from player list
	 * 
	 * @param player
	 * @return boolean Succes
	 */
	public static boolean PlayerLeave(Player player) {
		if(inGame.contains(player)) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (2*20), 1, true, false));
			player.setGameMode(GameMode.SURVIVAL);
			player.setCanPickupItems(false);
			player.teleport(player.getBedSpawnLocation());
			player.getInventory().clear();
			
			if(!player.isDead()) {
				player.setFoodLevel(20);
				player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
			}
			
			inGame.remove(player);
		}
		else {
			msg.sendErrorMessage("You are not in game!", player);
		}
		
		return true;
	}
	
	/**
	 * Run All events needed on game start
	 */
	public static void Start() {
		if(!Bukkit.getOnlinePlayers().isEmpty() && !Deathmatch.InMaintenance && !Deathmatch.InDebug) {
			//Kick all pre-joined players
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.kickPlayer("§l§bServer is Restarting... \n§r§3Please join back in a few seconds");
			}
		}
	}
	
	/**
	 * Run All events on game stop
	 */
	public static void Stop() {
		
	}
}
