package org.rspeekenbrink.deathmatch.classes.chests;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ChestGeneral extends ChestBase {
	/**
	 * Constructor
	 */
	public ChestGeneral() {
		initializeChest();
	}

	/**
	 * Initialize the chest
	 */
	private void initializeChest() {
		this.chestTitle = "General Chest";
		this.chestDisplayTitle = ChatColor.GRAY + "General Chest";
		this.isPlaceAble = true;
		
		ItemStack item = new ItemStack(Material.POTATO_ITEM, 1);
		chestItems.add(item);
	}

}
