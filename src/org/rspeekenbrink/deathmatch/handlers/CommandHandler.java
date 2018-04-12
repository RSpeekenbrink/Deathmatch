package org.rspeekenbrink.deathmatch.handlers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.rspeekenbrink.deathmatch.interfaces.SubCommand;
import org.rspeekenbrink.deathmatch.managers.MessageManager;
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
	private HashMap < String, Integer > helpinfo;
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
		commands.put("reload", new Reload());
		commands.put("spawn", new Spawn(plugin));
	}

	/**
	 * Setup Help info for each command
	 */
	private void setupHelp() {
		
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
				//TODO: Handle Help
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
		//TODO: Show Help
	}
	
	
}
