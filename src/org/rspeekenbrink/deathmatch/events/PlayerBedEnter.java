package org.rspeekenbrink.deathmatch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

public class PlayerBedEnter implements Listener {
	MessageManager msg = MessageManager.getInstance();
	
	@EventHandler
	public void onPlayerBedEnterEvent(PlayerBedEnterEvent e) {
		msg.sendErrorMessage("You can't sleep now!", e.getPlayer());
		e.setCancelled(true);
	}
}
