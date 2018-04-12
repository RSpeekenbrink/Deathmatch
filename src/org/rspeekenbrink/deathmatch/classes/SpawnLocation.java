package org.rspeekenbrink.deathmatch.classes;

import org.bukkit.Location;
import org.bukkit.World;

public class SpawnLocation extends Location {

	public static enum SpawnType {
		MAIN(0), GAME(1);
		
	 private final int value;
	    private SpawnType(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public SpawnType type;
	
	public SpawnLocation(World world, double x, double y, double z, SpawnType type) {
		super(world, x, y, z);
		this.type = type;
	}
	
	public SpawnLocation(Location location, SpawnType type) {
		super(location.getWorld(), location.getX(), location.getY(), location.getZ());
		this.type = type;
	}

}
