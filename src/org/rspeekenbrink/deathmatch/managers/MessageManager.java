package org.rspeekenbrink.deathmatch.managers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * MessageManager class to make it possible to easily send messages using the plugin's standard markdown. This Manager is singleton and will be required to
 * be defined using the getInstace() function
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class MessageManager {
	private static MessageManager instance;
	public String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "DEATHMATCH" + ChatColor.DARK_GRAY + "]" + ChatColor.WHITE;
	public ArrayList<String> OpNotifications = new ArrayList<String>();
	
	/**
	 * Constructor
	 */
	public MessageManager() {
		
	}
	
	/**
	 * Requests the instance of the MessageManager Class, if this does not exist create the instance.
	 * 
	 * @return MessageManager
	 */
	public static MessageManager getInstance() {
		if(instance == null) {
			instance = new MessageManager();
		}
		return instance;
	}
	
	/**
	 * Send a title message
	 * 
	 * @param title String Will not display if null
	 * @param subtitle String Will not display if null
	 * @param player Player Player to send the message to
	 * @param fadeIn int time it takes to fade in in ticks
	 * @param stay int time it takes to stay in ticks
	 * @param fadeOut int time it takes to fade out in ticks
	 */
	public void sendTitleMessage(String title, String subtitle, Player player, int fadeIn, int stay, int fadeOut) {
		player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
	}
	
	/**
	 * Send a message to a player using the plugins markdown
	 * 
	 * @param msg String containing the message to send
	 * @param player Player object of the player to send the message to
	 */
	public void sendMessage(String msg, Player player) {
		player.sendMessage(Prefix + " " + msg );
	}
	
	/**
	 * Send an Error message to a player using the plugins markdown
	 * 
	 * @param msg String containing the message to send
	 * @param player Player object of the player to send the message to
	 */
	public void sendErrorMessage(String msg, Player player) {
		player.sendMessage(Prefix + ChatColor.RED + " " + msg );
	}
	
	/**
	 * Broadcast a message for the whole server to see using the plugins mardown
	 * 
	 * @param msg String containing the message to send
	 */
	public void broadcastMessage(String msg) {
		Bukkit.broadcastMessage(Prefix + " " + msg );
	}
	
	/**
	 * Send OP Notifications to player
	 * 
	 * @param player Player object of the player to send the message to
	 */
	public void sendOpNotifications(Player player) {
		for (String message: OpNotifications) {
		    sendMessage(message, player);
		}
	}
	
}
