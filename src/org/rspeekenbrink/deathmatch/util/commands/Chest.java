package org.rspeekenbrink.deathmatch.util.commands;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.rspeekenbrink.deathmatch.Deathmatch;
import org.rspeekenbrink.deathmatch.handlers.ChestHandler;
import org.rspeekenbrink.deathmatch.interfaces.SubCommand;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

/**
 * Chest Command handling
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class Chest implements SubCommand {
	private MessageManager msg = MessageManager.getInstance();
	private ChestHandler chestHandler = ChestHandler.getInstance();

	@Override
	public boolean onCommand(Player player, String[] args) {
		if(!player.hasPermission("deathmatch.chest.place")) {
			msg.sendNoPermissionMessage(player);
			return false;
		}
		
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("place")) {
				if(!Deathmatch.InMaintenance) {
					msg.sendErrorMessage("The server is not in Maintenance mode!", player);
					return false;
				}
				player.setGameMode(GameMode.CREATIVE);
				chestHandler.addChestsToInventory(player);
				msg.sendMessage("Placeable Chests added to your inventory!", player);
				return true;
			}
		}
		msg.sendErrorMessage("Incorrect command sytax! Use /dm help to see the list of commands", player);
		return false;
	}

	@Override
	public String help(Player p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String permission() {
		return "deathmatch.chest.place";
	}

}
