package org.rspeekenbrink.deathmatch.handlers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.rspeekenbrink.deathmatch.interfaces.SubCommand;
import org.rspeekenbrink.deathmatch.managers.MessageManager;
import org.rspeekenbrink.deathmatch.util.commands.Join;
import org.rspeekenbrink.deathmatch.util.commands.Leave;
import org.rspeekenbrink.deathmatch.util.commands.Reload;
import org.rspeekenbrink.deathmatch.util.commands.Spawn;

/**
 * CommandHandler, handling all the commands the plugin has
 * It is registered upon plugin load in the Startup class.
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class CommandHandler implements CommandExecutor {
	private Plugin plugin;
	private HashMap < String, SubCommand > commands;
	private HashMap < String, Integer > helpinfo; //ints: Commands(1), Admin Commands(2)
	private MessageManager msgManager = MessageManager.getInstance();
	
	/**
	 * Class constructor that also loads all commands etc on construction.
	 * 
	 * @param plugin
	 */
	public CommandHandler(Plugin plugin) {
		this.plugin = plugin;
		commands = new HashMap < String, SubCommand > ();
		helpinfo = new HashMap < String, Integer > ();
		setupCommands();
		setupHelp();
	}
	
	/**
	 * Binds the command to a command handle class
	 */
	private void setupCommands() {
		commands.put("join", new Join());
		commands.put("leave", new Leave());
		commands.put("reload", new Reload());
		commands.put("spawn", new Spawn(plugin));
	}

	/**
	 * Setup Help info for each command
	 */
	private void setupHelp() {
		//General
		helpinfo.put("join", 1);
		helpinfo.put("leave", 1);
		
		//Admin
		helpinfo.put("reload", 2);
		helpinfo.put("spawn", 2);
	}

	/**
	 * Function called on command execution, this function will check if the command has enough arguments and if that argument command (SubCommand) exists
	 * and execute the correct SubCommand class.
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd1, String commandLabel, String[] args) {
		Player player = (Player) sender;
		
		if (cmd1.getName().equalsIgnoreCase("deathmatch")) {
			if (args == null || args.length < 1) {
				msgManager.sendMessage("Type /deathmatch help for command information", player);
				return true;
			}
			if (args[0].equalsIgnoreCase("help")) {
				if (args.length == 1) {
					help(player, 1);
					return true;
				}
				else {
					if(args[1].equalsIgnoreCase("admin")) {
						help(player,2);
						return true;
					}
					help(player,1);
				}
				return true;
			}
			
			//Convert command arguments into readable objects for command functions
			String sub = args[0];
			Vector < String > l = new Vector < String > ();
			l.addAll(Arrays.asList(args));
			l.remove(0);
			args = (String[]) l.toArray(new String[0]);
			
			if (!commands.containsKey(sub)) {
				msgManager.sendMessage("Command Invalid", player);
				msgManager.sendMessage("Type /deathmatch help for command information", player);
				return true;
			}
			
			try {
				//Executes the onCommand function of the SubCommand class
				commands.get(sub).onCommand(player, args);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	public void help (Player p, int page) {
		if (page == 1) {
			p.sendMessage(ChatColor.DARK_GRAY + "------------ " + ChatColor.DARK_AQUA + msgManager.Prefix + " Commands" + ChatColor.DARK_GRAY + " ------------");
		}
		if (page == 2) {
			if(!p.isOp() && !p.hasPermission("deathmatch.help.admin")) {
				msgManager.sendErrorMessage("You do not have permission to view this help page", p);
				return;
			}
			p.sendMessage(ChatColor.DARK_GRAY + "------------ " + ChatColor.DARK_AQUA + msgManager.Prefix + " Admin Commands" + ChatColor.DARK_GRAY + " ------------");
		}
		
		for (String command : commands.keySet()) {
			try{
				if (helpinfo.get(command) == page) {
					p.sendMessage(commands.get(command).help(p));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
}
