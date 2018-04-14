package org.rspeekenbrink.deathmatch.util.commands;

import org.bukkit.entity.Player;
import org.rspeekenbrink.deathmatch.interfaces.SubCommand;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

/**
 * Reload Subcommand Class, Handles the Reload subcommand defined in the CommandHandler
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class Reload implements SubCommand {
	private MessageManager msg = MessageManager.getInstance();

	@Override
	public boolean onCommand(Player player, String[] args) {
		if(!player.hasPermission(permission())) {
			msg.sendNoPermissionMessage(player);
			return false;
		}
		return false;
	}

	@Override
	public String help(Player p) {
		return msg.convertHelpInfo("/deathmatch reload", "Reload the plugin settings and config");
	}

	@Override
	public String permission() {
		return "deathmatch.reload";
	}

}
