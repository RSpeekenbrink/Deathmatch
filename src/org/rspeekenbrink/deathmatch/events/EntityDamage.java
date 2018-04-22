package org.rspeekenbrink.deathmatch.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.rspeekenbrink.deathmatch.Deathmatch;
import org.rspeekenbrink.deathmatch.Game;

/**
 * EntityDamage, Handles EntityDamage Event
 * @see https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/event/entity/EntityDamageEvent.html
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class EntityDamage implements Listener {
	
	@EventHandler
	public void onEntityDamager(EntityDamageEvent e) {
		if(e.getEntity().getType().equals(EntityType.ARMOR_STAND) || e.getEntity().getType().equals(EntityType.ITEM_FRAME)) {
			if(!Deathmatch.InMaintenance) {
				e.setCancelled(true);
				return;
			}
		}
		
		if(e.getEntity().getType() == EntityType.PLAYER) {
			//player
			Player player = (Player) e.getEntity();
			if(!Game.inGame.contains(player)) {
				e.setCancelled(true); //disable damage out of game
			}
		}
	}
}
