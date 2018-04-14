package org.rspeekenbrink.deathmatch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

public class SignChange implements Listener {
	MessageManager msg = MessageManager.getInstance();
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		
	}	

}
