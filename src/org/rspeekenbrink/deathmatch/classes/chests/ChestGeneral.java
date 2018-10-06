package org.rspeekenbrink.deathmatch.classes.chests;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * ChestGeneral, chest type, implements ChestBase
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 * @see 		ChestBase
 */
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
		
		ItemStack item = new ItemStack(Material.POTATO, 1);
		chestItems.add(item);
		
		item = new ItemStack(Material.BAKED_POTATO, 1);
		chestItems.add(item);
		
		item = new ItemStack(Material.BAKED_POTATO, 2);
		chestItems.add(item);
		
		item = new ItemStack(Material.WOODEN_HOE, 1);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setLore(Arrays.asList("Don't be sad, its just a wooden hoe!"));
		item.setItemMeta(itemMeta);
		chestItems.add(item);
		
		item = new ItemStack(Material.STONE_SWORD, 1);
		chestItems.add(item);
		
		item = new ItemStack(Material.STONE_AXE, 1);
		chestItems.add(item);
		
		item = new ItemStack(Material.WOODEN_AXE, 1);
		chestItems.add(item);
		
		item = new ItemStack(Material.CARROT, 1);
		chestItems.add(item);
		
		item = new ItemStack(Material.LEATHER_HELMET, 1);
		chestItems.add(item);
		
		item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		chestItems.add(item);
		
		item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		chestItems.add(item);
		
		item = new ItemStack(Material.LEATHER_BOOTS, 1);
		chestItems.add(item);
		
		item = new ItemStack(Material.BOW, 1);
		chestItems.add(item);
		
		item = new ItemStack(Material.ARROW, 1);
		chestItems.add(item);
		
		item = new ItemStack(Material.ARROW, 2);
		chestItems.add(item);
		
		item = new ItemStack(Material.ARROW, 3);
		chestItems.add(item);
	}

}
