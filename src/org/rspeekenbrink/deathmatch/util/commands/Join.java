package org.rspeekenbrink.deathmatch.util.commands;

import org.bukkit.entity.Player;
import org.rspeekenbrink.deathmatch.Game;
import org.rspeekenbrink.deathmatch.interfaces.SubCommand;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

public class Join implements SubCommand {
	private MessageManager msg = MessageManager.getInstance();

	@Override
	public boolean onCommand(Player player, String[] args) {
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
