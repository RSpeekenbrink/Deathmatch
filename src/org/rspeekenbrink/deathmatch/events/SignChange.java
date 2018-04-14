package org.rspeekenbrink.deathmatch.events;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.rspeekenbrink.deathmatch.managers.MessageManager;

public class SignChange implements Listener {
	MessageManager msg = MessageManager.getInstance();
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if(e.getPlayer().hasPermission("deathmatch.place.sign") || e.getPlayer().isOp()) {
			if(e.getLine(0).equalsIgnoreCase("[dmjoin]")) {
				e.setLine(0, "§8[§8Death§8Match§8]");
				e.setLine(1, "§3Join");
				e.setLine(2, "§r§fClick here to join");
				e.setLine(3, "§r§fDeathmatch!");
			}
		}
	}	

}
