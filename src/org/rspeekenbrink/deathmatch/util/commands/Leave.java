package org.rspeekenbrink.deathmatch.util.commands;

import org.bukkit.entity.Player;
import org.rspeekenbrink.deathmatch.Game;
import org.rspeekenbrink.deathmatch.interfaces.SubCommand;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

/**
 * Leave Command handling
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class Leave implements SubCommand {
	MessageManager msg = MessageManager.getInstance();

	@Override
	public boolean onCommand(Player player, String[] args) {
		if(!player.hasPermission(permission())) {
			msg.sendNoPermissionMessage(player);
			return false;
		}
		
		Game.PlayerLeave(player);
		return true;
	}

	@Override
	public String help(Player p) {
		return msg.convertHelpInfo("/deathmatch leave", "Leave the game");
	}

	@Override
	public String permission() {
		return "deathmatch.leave";
	}

}
