package org.rspeekenbrink.deathmatch.util.commands;

import org.bukkit.entity.Player;
import org.rspeekenbrink.deathmatch.Game;
import org.rspeekenbrink.deathmatch.interfaces.SubCommand;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

/**
 * Join Command handling
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class Join implements SubCommand {
	private MessageManager msg = MessageManager.getInstance();

	@Override
	public boolean onCommand(Player player, String[] args) {
		if(!player.hasPermission(permission())) {
			msg.sendNoPermissionMessage(player);
			return false;
		}
		
		Game.PlayerJoin(player);
		return true;
	}

	@Override
	public String help(Player p) {
		return msg.convertHelpInfo("/deathmatch join", "Join Deathmatch");
	}

	@Override
	public String permission() {
		return "deathmatch.join";
	}

}
