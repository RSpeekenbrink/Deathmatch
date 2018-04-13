package org.rspeekenbrink.deathmatch.managers;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.rspeekenbrink.deathmatch.classes.SpawnLocation;
import org.rspeekenbrink.deathmatch.util.Logger;

public class DatabaseManager {
	private static DatabaseManager instance;
	private Plugin plugin;
	private Logger logger;
	
	private String dbname;
	private Connection connection;
	
	public static String SQL_TABLE_SPAWNS = "tbl_spawns";
	public static String SQL_TABLE_CHESTS = "tbl_chests";
	
	private static String SQL_TABLE_SPAWNS_VARS = "(type,world,x,y,z)";
	private static String SQL_TABLE_CHESTS_VARS = "(type,world,x,y,z)";
	
    private static String SQL_CREATE_SPAWNSTABLE = "CREATE TABLE IF NOT EXISTS " + SQL_TABLE_SPAWNS + " (" +
            "`id` INTEGER PRIMARY KEY AUTOINCREMENT," + 
            "`type` int NOT NULL," +
            "`world` varchar(99) NOT NULL," +
            "`x` int NOT NULL," +
            "`y` int NOT NULL," +
            "`z` int NOT NULL" +
            ");";
   
    private static String SQL_CREATE_CHESTTABLE = "CREATE TABLE IF NOT EXISTS " + SQL_TABLE_CHESTS + " (" +
           "`id` INTEGER PRIMARY KEY AUTOINCREMENT," + 
           "`type` int NOT NULL," +
           "`world` varchar(99) NOT NULL," +
           "`x` int NOT NULL," +
           "`y` int NOT NULL," +
           "`z` int NOT NULL" + 
           ");";
	
	/**
	 * Constructor
	 */
	public DatabaseManager(Plugin plugin) {
		this.plugin = plugin;
		this.logger = new Logger(plugin);
		dbname = plugin.getConfig().getString("dbFile");
	}
	
	/**
	 * Requests the instance of the SettingsManager Class, if this does not exist create the instance.
	 * 
	 * @param Plugin plugin
	 * @return SettingsManager
	 */
	public static DatabaseManager getInstance(Plugin plugin) {
		if(instance == null) {
			instance = new DatabaseManager(plugin);
		}
		return instance;
	}

	public static DatabaseManager getInstance() {
		if(instance == null) {
			return null;
		}
		return instance;
	}
	
	
	/**
	 * Get Connection to the SQLite DB
	 * 
	 * @return Connection
	 */
    public Connection getSQLConnection() {
        File dataFolder = new File(plugin.getDataFolder(), dbname);
        if (!dataFolder.exists()){
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                logger.severe("File write error: "+ dbname);
            }
        }
        try {
            if(connection!=null&&!connection.isClosed()){
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
        } catch (SQLException ex) {
        	logger.severe("SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
        	logger.severe( "You need the SQLite JBDC library. Google it. Put it in /lib folder.");
        }
        return null;
    }

    /**
     * Initialize the database and create tables if they do not exist
     */
    public void load() {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(SQL_CREATE_SPAWNSTABLE);
            s.executeUpdate(SQL_CREATE_CHESTTABLE);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(null,null,connection);
    }
    
    /**
     * Get list of spawnlocations of a specific type
     * 
     * @param SpawnLocation.SpawnType type
     * @return List<SpawnLocation> results
     */
	public List<SpawnLocation> getSpawnLocations(SpawnLocation.SpawnType type) {
    	connection = getSQLConnection();
    	PreparedStatement ps = null;
        ResultSet rs = null;
        List<SpawnLocation> result = new ArrayList<SpawnLocation>();
        
        try {
        	ps = connection.prepareStatement("SELECT * FROM " + SQL_TABLE_SPAWNS + " WHERE type = " + type.getValue());
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
          		World world = Bukkit.getWorld(UUID.fromString(rs.getString("world")));
        		if(world != null && type != null)
        			result.add(new SpawnLocation(world, rs.getDouble("x"), rs.getDouble("y"), rs.getDouble("z"), type));
        		else
        			logger.severe("Can't resolve world");
        	}
        	
        } catch (SQLException ex) {
        	ex.printStackTrace();
        } finally {
        	close(ps, rs, connection);
        }
        
        return result;
    }
    
    /**
     * Insert a SpawnLocation into the Database
     * 
     * @param SpawnLocation location
     */
    public void insertSpawnLocation(SpawnLocation location) {
    	connection = getSQLConnection();
    	PreparedStatement ps = null;
    	try {
    		ps = connection.prepareStatement("INSERT INTO " + SQL_TABLE_SPAWNS + " " + SQL_TABLE_SPAWNS_VARS + "VALUES (?,?,?,?,?)" );
    		ps.setInt(1, location.type.getValue());
    		ps.setString(2, location.getWorld().getUID().toString());
    		ps.setDouble(3, location.getX());
    		ps.setDouble(4, location.getY());
    		ps.setDouble(5, location.getZ());
    		logger.finest("Executing Query: " + ps.toString());
    		ps.executeUpdate();
    		
    	} catch (SQLException ex) {
        	ex.printStackTrace();
        	logger.severe("Couldn't save Spawn Location in DB; " + ex.getMessage());
        } finally {
        	close(ps, null, connection);
        }
    }
    
    /**
     * Remove All Spawns of a specific type
     * 
     * @param SpawnType
     */
    public void deleteAllSpawnLocations(SpawnLocation.SpawnType type) {
    	connection = getSQLConnection();
    	PreparedStatement ps = null;
    	try {
    		ps = connection.prepareStatement("DELETE FROM " + SQL_TABLE_SPAWNS + " WHERE type = " + type.getValue() );
    		ps.executeUpdate();
    	} catch (SQLException ex) {
        	ex.printStackTrace();
        	logger.severe("Couldn't remove Spawn Location in DB; " + ex.getMessage());
        } finally {
        	close(ps, null, connection);
        }
    }

    /**
     * Close connections
     * 
     * @param ps
     * @param rs
     * @param conn
     */
    public void close(PreparedStatement ps, ResultSet rs, Connection conn){
        try {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
            if (conn != null)
            	conn.close();
            else
            	connection.close();
        } catch (SQLException ex) {
            logger.severe("Error occured whilst closing DB connection");
            ex.printStackTrace();
        }
    }
}
