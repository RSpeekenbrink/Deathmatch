package org.rspeekenbrink.deathmatch.classes;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * SpawnLocation to store a spawn location (Main or ingame spawn)
 * 
 * @author 		Remco Speekenbrink
 * @version 	1.0
 * @since       1.0
 */
public class SpawnLocation extends Location {

	/**
	 * SpawnType (Game or Main) enum (Main = 0, Game = 1)
	 * 
	 * @author 		Remco Speekenbrink
	 * @version 	1.0
	 * @since       1.0
	 */
	public static enum SpawnType {
		MAIN(0), GAME(1);
		
		private final int value;
	 
		/**
		 * Constructor
		 * @param value int
		 */
	    private SpawnType(int value) {
	        this.value = value;
	    }
	    
	    /**
		 * Get int of enum
		 * @return int Enum number
		 */
	    public int getValue() {
	        return value;
	    }
	}
	
	public SpawnType type;
	
	/**
	 * Constructor
	 * 
	 * @param world
	 * @param x 
	 * @param y
	 * @param z
	 * @param type SpawnType
	 */
	public SpawnLocation(World world, double x, double y, double z, SpawnType type) {
		super(world, x, y, z);
		this.type = type;
	}
	
	/*
	 * Constructor
	 * 
	 * @param location Location
	 * @param type SpawnType
	 */
	public SpawnLocation(Location location, SpawnType type) {
		super(location.getWorld(), location.getX(), location.getY(), location.getZ());
		this.type = type;
	}

}
