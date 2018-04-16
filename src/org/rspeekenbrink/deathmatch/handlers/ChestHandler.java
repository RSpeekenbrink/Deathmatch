package org.rspeekenbrink.deathmatch.handlers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.rspeekenbrink.deathmatch.classes.chests.ChestBase;
import org.rspeekenbrink.deathmatch.managers.DatabaseManager;

public class ChestHandler {
	private static ChestHandler instance;
	private DatabaseManager db = DatabaseManager.getInstance();
	private HashMap<String, ChestBase> chestTypes = new HashMap<String, ChestBase>();
	
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
	 * Register placed chest
	 * 
	 * @param chestPlaced
	 * @param locationPlaced
	 */
	public void placeChest(ItemStack chestPlaced, Location locationPlaced) {
		String type = getChestType(chestPlaced);
		if(type != null) {
			db.addChest(locationPlaced, type);
		}
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
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}
}
