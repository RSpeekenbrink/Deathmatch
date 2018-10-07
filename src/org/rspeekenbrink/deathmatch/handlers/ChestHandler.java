package org.rspeekenbrink.deathmatch.handlers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.rspeekenbrink.deathmatch.Deathmatch;
import org.rspeekenbrink.deathmatch.classes.chests.ChestBase;
import org.rspeekenbrink.deathmatch.managers.DatabaseManager;
import org.rspeekenbrink.deathmatch.util.Logger;

/**
 * ChestHandler has all chest handling functions
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class ChestHandler {
	private static ChestHandler instance;
	private DatabaseManager db = DatabaseManager.getInstance();
	private HashMap<String, ChestBase> chestTypes = new HashMap<String, ChestBase>();
	private HashMap<Location, String> chestCache = new HashMap<Location, String>();
	private Logger logger;
	
	public ChestHandler() {
		logger = new Logger(Deathmatch.main);
	}
	
	/**
	 * Requests the instance of the MessageManager Class, if this does not exist create the instance.
	 * 
	 * @return ChestManager
	 */
	public static ChestHandler getInstance() {
		if(instance == null) {
			instance = new ChestHandler();
		}
		return instance;
	}
	
	/**
	 * Register Chest Type
	 * 
	 * @param typeTitle String (same as in DB)
	 * @param chestClass ChestBase to handle items etc
	 */
	public void registerChestType(String typeTitle, ChestBase chestClass) {
		chestTypes.put(typeTitle, chestClass);
	}
	
	/**
	 * Get item drop from specific chest type
	 * 
	 * @param chestType String from db
	 * @return ItemStack Item of chestType's drop function
	 * @throws ClassNotFoundException 
	 */
	public ItemStack getChestDropItem(String chestType) throws ClassNotFoundException {
		if(chestTypes.containsKey(chestType)) {
			return chestTypes.get(chestType).DropItem();
		}
		else {
			throw new ClassNotFoundException();
		}
	}
	
	/**
	 * Get item drop from specific chest type
	 * 
	 * @param location Location of chest
	 * @return ItemStack Item of chestType's drop function
	 */
	public ItemStack getChestDropItem(Location location) {
		if(isGameChest(location)) {
			return chestTypes.get(chestCache.get(location)).DropItem();
		}
		else {
			return null;
		}
	}
	
	/**
	 * Check if Item is placeable chest and return the chest Type
	 * 
	 * @param chest Itemstack to check
	 * @return
	 */
	public String getChestType(ItemStack chest) {
		if(isPlaceAbleChest(chest)) {
			ItemMeta itemMeta = chest.getItemMeta();
			return itemMeta.getLore().get(0);
		}
		return null;
	}
	
	/**
	 * Check if Item is placeable chest
	 * 
	 * @param chestToCheck ItemStack to check
	 * @return
	 */
	public boolean isPlaceAbleChest(ItemStack chestToCheck) {
		if(chestToCheck.getType() == Material.CHEST && chestToCheck.hasItemMeta()) {
			ItemMeta itemMeta = chestToCheck.getItemMeta();
			if(itemMeta.hasLore()) {
				return chestTypes.containsKey(itemMeta.getLore().get(0));
			}
		}
		
		return false;
	}
	
	/**
	 * Returns if current location is game chest
	 * 
	 * @param location Location to check
	 * @return
	 */
	public boolean isGameChest(Location location) {
		return chestCache.containsKey(location);
	}
	
	/**
	 * Register placed chest
	 * 
	 * @param chestPlaced
	 * @param locationPlaced
	 */
	public void placeChest(ItemStack chestPlaced, Location locationPlaced) {
		logger.finer("New chest placed!");
		String type = getChestType(chestPlaced);
		if(type != null) {
			db.addChest(locationPlaced, type);
		}
	}
	
	/**
	 * Cache a chest
	 * 
	 * @param location Location
	 * @param type Chest type
	 */
	public void cacheChest(Location location, String type) {
		chestCache.put(location, type);
	}
	
	/**
	 * Drop Item and handle chest respawn
	 */
	public void dropItem(Location location, Plugin plugin) {
		if(!isGameChest(location)) return;
		
		ItemStack item = getChestDropItem(location);
		
		location.getBlock().setType(Material.AIR);
		
		location.getWorld().dropItem(location, item);
		
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			@Override
			public void run() {
				location.getBlock().setType(Material.CHEST);
			}
		}, (20*30));
		
		
	}
	
	/**
	 * Add Placeable chests to inventory of player (used on chest place command)
	 * 
	 * @param player Player to give the chests to
	 */
	public void addChestsToInventory(Player player) {
		PlayerInventory i = player.getInventory();
		i.clear();
		int x = 0;
		Iterator<Entry<String, ChestBase>> it = chestTypes.entrySet().iterator();
		while (it.hasNext()) {
			if(!(x < 36)) break;
			@SuppressWarnings("rawtypes")
			HashMap.Entry pair = (HashMap.Entry)it.next();
			ChestBase chestType = (ChestBase) pair.getValue();
			if(!chestType.isPlaceAble()) break;
			ItemStack chest = new ItemStack(Material.CHEST);
			ItemMeta chestMeta = chest.getItemMeta();
			chestMeta.setDisplayName(chestType.chestDisplayTitle);
			chestMeta.setLore(Arrays.asList((String)pair.getKey()));
			chest.setItemMeta(chestMeta);
			i.setItem(x, chest);
			x++;
	    }
	}
}
