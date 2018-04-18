package org.rspeekenbrink.deathmatch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplode implements Listener {
	@EventHandler
	public void onExplosionPrime(EntityExplodeEvent e) {
        e.blockList().clear();
    }
}
