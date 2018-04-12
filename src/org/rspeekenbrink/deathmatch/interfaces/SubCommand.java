package org.rspeekenbrink.deathmatch.interfaces;

import org.bukkit.entity.Player;

/**
 * SubCommand Interface used in the CommandHandler and for the command classes
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public interface SubCommand {
    public boolean onCommand(Player player, String[] args);
    public String help(Player p);
    public String permission();
}