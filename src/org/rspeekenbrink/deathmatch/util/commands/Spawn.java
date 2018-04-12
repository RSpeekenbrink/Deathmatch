package org.rspeekenbrink.deathmatch.util.commands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.rspeekenbrink.deathmatch.classes.SpawnLocation;
import org.rspeekenbrink.deathmatch.interfaces.SubCommand;
import org.rspeekenbrink.deathmatch.managers.DatabaseManager;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

public class Spawn implements SubCommand {
	private Plugin plugin;
	private DatabaseManager db = DatabaseManager.getInstance(plugin);
	private MessageManager msg = MessageManager.getInstance();
	
	public Spawn(Plugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(Player player, String[] args) {
		if(args.length == 2) {
			if(args[0].equals("set")) {
				SpawnLocation.SpawnType type;
				
				switch(args[1]) {
					case "main":
						type = SpawnLocation.SpawnType.MAIN;
						SpawnLocation newSp = new SpawnLocation(player.getLocation(), type);
						db.insertSpawnLocation(newSp);
						//TODO: REMOVE OLD MAIN SPAWN
						msg.sendMessage("Main Spawn Set Succesfully", player);
						break;
					case "game":
						//TODO: HANDLE
						break;
					default:
						msg.sendErrorMessage("Unknown Spawn type", player);
						break;
				}
			}
			
		}
		return false;
	}

	@Override
	public String help(Player p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String permission() {
		// TODO Auto-generated method stub
		return null;
	}

}
