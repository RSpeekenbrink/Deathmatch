package org.rspeekenbrink.deathmatch.util.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.rspeekenbrink.deathmatch.classes.PlayerStats;
import org.rspeekenbrink.deathmatch.interfaces.SubCommand;
import org.rspeekenbrink.deathmatch.managers.DatabaseManager;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

public class Stats implements SubCommand {
	MessageManager msg = MessageManager.getInstance();
	DatabaseManager db = DatabaseManager.getInstance();

	@Override
	public boolean onCommand(Player player, String[] args) {
		if(!player.hasPermission(permission())) {
			msg.sendNoPermissionMessage(player);
			return false;
		}
		
		if(args.length == 0) {
			if(!db.playerExists(player)) {
				msg.sendErrorMessage("To view your stats you should join Deathmatch first!", player);
				return false;
			}
			
			PlayerStats playerStats = db.getPlayerStats(player);
			msg.sendMessage(ChatColor.GREEN + "------------ Deathmatch Stats: -----------", player);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z"); 
			sdf.setTimeZone(TimeZone.getTimeZone("Europe/Amsterdam"));
			Date date = new Date(playerStats.firstJoin); 
			msg.sendMessage(ChatColor.GREEN + "First Joined a game: " + sdf.format(date), player);
			date = new Date(playerStats.lastJoin); 
			msg.sendMessage(ChatColor.GREEN + "Last Joined a game: " + sdf.format(date), player);
			msg.sendMessage(ChatColor.GREEN + "Kills: " + playerStats.kills, player);
			return true;
		}
		
		return false;
	}

	@Override
	public String help(Player p) {
		return msg.convertHelpInfo("/deathmatch stats", "Show your stats");
	}

	@Override
	public String permission() {
		return "deathmatch.stats";
	}

}
