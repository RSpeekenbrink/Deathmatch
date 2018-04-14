package org.rspeekenbrink.deathmatch.handlers;

import java.util.HashMap;

import org.bukkit.inventory.ItemStack;
import org.rspeekenbrink.deathmatch.classes.chests.ChestBase;

public class ChestHandler {
	private static ChestHandler instance;
	private HashMap<String, ChestBase> chestTypes;
	
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
}
