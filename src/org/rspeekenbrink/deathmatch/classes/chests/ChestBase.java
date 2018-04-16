package org.rspeekenbrink.deathmatch.classes.chests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * ChestBase, base class for all chests, this is an example to be extended by all other chests
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class ChestBase {
	protected Collection<ItemStack> chestItems = new ArrayList<ItemStack>();
	protected boolean isPlaceAble = false;
	
	public String chestTitle = "";
	public String chestDisplayTitle = "";
	
	/**
	 * Return if chest is placeable
	 * @return boolean
	 */
	public boolean isPlaceAble() {
		return this.isPlaceAble;
	}
	
	/**
	 * Constructor
	 */
	public ChestBase() {
		initializeChest();
	}
	
	/**
	 * Initialize the chest
	 */
	private void initializeChest() {
		this.chestTitle = "Chest Base";
		this.chestDisplayTitle = ChatColor.RED + "Chest Base";
	}
	
	/**
	 * Return Item to drop
	 * 
	 * @return ItemStack items to drop when chest is opened
	 */
	public ItemStack DropItem() {
		Random randomizer = new Random();
		return (ItemStack) chestItems.toArray()[randomizer.nextInt(chestItems.size())];
	}
}
