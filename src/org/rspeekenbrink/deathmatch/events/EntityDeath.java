package org.rspeekenbrink.deathmatch.events;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.rspeekenbrink.deathmatch.Game;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

/**
 * EntityDeath Event will handle all points etc.
 * It is registered upon plugin load in the Startup class.
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class EntityDeath implements Listener {
	private MessageManager msg = MessageManager.getInstance();
	
	@EventHandler
	public void onPlayerDeath(EntityDeathEvent e) 
	{
		LivingEntity entity = e.getEntity();
		
		if(entity.getType() == EntityType.PLAYER) {
			//player died *sad*
			Player killed = (Player) entity;
			killed.getWorld().playSound(killed.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_DEATH, 1, 1);
			msg.broadcastMessage(ChatColor.GREEN + killed.getName() + " got killed by " + killed.getKiller().getName());
			Game.PlayerLeave(killed);
		}
	}
	
}
