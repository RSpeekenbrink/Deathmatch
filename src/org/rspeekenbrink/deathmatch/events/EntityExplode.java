package org.rspeekenbrink.deathmatch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

/**
 * EntityExplode event will handle entity explosions (Fireball, creepers etc.) and cancel the blockdamage
 * It is registered upon plugin load in the Startup class.
 * @see https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/entity/EntityExplodeEvent.html
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class EntityExplode implements Listener {
	@EventHandler
	public void onExplosionPrime(EntityExplodeEvent e) {
        e.blockList().clear();
    }
}
