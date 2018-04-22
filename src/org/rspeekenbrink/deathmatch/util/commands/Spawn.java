package org.rspeekenbrink.deathmatch.util.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.rspeekenbrink.deathmatch.classes.SpawnLocation;
import org.rspeekenbrink.deathmatch.interfaces.SubCommand;
import org.rspeekenbrink.deathmatch.managers.DatabaseManager;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

/**
 * Spawn Command handling
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class Spawn implements SubCommand {
	private Plugin plugin;
	private DatabaseManager db = DatabaseManager.getInstance(plugin);
	private MessageManager msg = MessageManager.getInstance();
	
	public Spawn(Plugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(Player player, String[] args) {
		if(!player.hasPermission(permission())) {
			msg.sendNoPermissionMessage(player);
			return false;
		}
		
		if(args.length == 2) {
			if(args[0].equalsIgnoreCase("add")) {
				SpawnLocation.SpawnType type;
				switch(args[1]) {
					case "main":
						type = SpawnLocation.SpawnType.MAIN;
						SpawnLocation newSp = new SpawnLocation(player.getLocation(), type);
						db.deleteAllSpawnLocations(type);
						db.insertSpawnLocation(newSp);
						msg.sendMessage(ChatColor.GREEN + "Main Spawn Set Succesfully", player);
						break;
					case "game":
						type = SpawnLocation.SpawnType.GAME;
						SpawnLocation newGs = new SpawnLocation(player.getLocation(), type);
						db.insertSpawnLocation(newGs);
						msg.sendMessage(ChatColor.GREEN + "Game Spawn added", player);
						break;
					default:
						msg.sendErrorMessage("Unknown Spawn type", player);
						break;
				}
				return true;
			}
			if(args[0].equalsIgnoreCase("delete")) {
				SpawnLocation.SpawnType type;
				switch(args[1]) {
				case "main":
					type = SpawnLocation.SpawnType.MAIN;
					db.deleteAllSpawnLocations(type);
					msg.sendMessage(ChatColor.GREEN + "Main Spawn Deleted Succesfully", player);
					break;
				case "game":
					type = SpawnLocation.SpawnType.GAME;
					db.deleteAllSpawnLocations(type);
					msg.sendMessage(ChatColor.GREEN + "Game Spawns Deleted Succesfully", player);
					break;
				default:
					msg.sendErrorMessage("Unknown Spawn type", player);
					break;
				}
			}
		}
		msg.sendErrorMessage("Incorrect command sytax! Use /dm help to see the list of commands", player);
		return false;
	}

	@Override
	public String help(Player p) {
		return msg.convertHelpInfo("/deathmatch spawn [add/delete] [main/game]", "Add or Delete the main or a game spawn");
	}

	@Override
	public String permission() {
		return "deathmatch.spawn.modify";
	}

}
