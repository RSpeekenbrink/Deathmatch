package org.rspeekenbrink.deathmatch;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.rspeekenbrink.deathmatch.managers.DatabaseManager;

/**
 * Static Class containing all main game functions and loops
 * It is registered upon plugin load in the Startup class.
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public final class Game {
	private DatabaseManager db = DatabaseManager.getInstance();
	private static ArrayList<Player> inGame = new ArrayList<Player>();
	
	public static void PlayerJoin(Player player) {
		inGame.add(player);
	}
	
	public static void Start() {
		
	}
	
	public static void Stop() {
		
	}
}
