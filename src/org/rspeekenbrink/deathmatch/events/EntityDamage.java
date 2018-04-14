package org.rspeekenbrink.deathmatch.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.rspeekenbrink.deathmatch.Game;

public class EntityDamage implements Listener {
	
	@EventHandler
	public void onEntityDamager(EntityDamageEvent e) {
		if(e.getEntity().getType() == EntityType.PLAYER) {
			//player
			Player player = (Player) e.getEntity();
			if(!Game.inGame.contains(player)) {
				e.setCancelled(true); //disable damage out of game
			}
			else {
				
			}
		}
	}
}
