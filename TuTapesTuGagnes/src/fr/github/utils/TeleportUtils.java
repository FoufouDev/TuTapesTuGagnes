package fr.github.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class TeleportUtils {
	
	private List<Location> spawnLocations;
	
	public TeleportUtils(){
		spawnLocations = new ArrayList<>();
		spawnLocations.add(new Location(Bukkit.getWorlds().get(0), -4, 82, 55));
		spawnLocations.add(new Location(Bukkit.getWorlds().get(0), -4, 82, 34));
	}
	public List<Location> getSpawnList(){
		return spawnLocations;
	}
	public Location getRandomSpawn(){
		Location loc = getSpawnList().get(new Random().nextInt(getSpawnList().size()));
		getSpawnList().remove(loc);
		return loc;
	}
	public Location getSpawnLocation(){
		return new Location(Bukkit.getWorlds().get(0), -4, 82, 34);
	}
}
