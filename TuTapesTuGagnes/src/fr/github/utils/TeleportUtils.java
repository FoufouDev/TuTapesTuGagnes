package fr.github.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TeleportUtils {
	
	private List<Location> spawnLocations;
	
	public TeleportUtils(){
		spawnLocations = new ArrayList<>();
		spawnLocations.add(new Location(Bukkit.getWorlds().get(0), 30, 55, 30));
		spawnLocations.add(new Location(Bukkit.getWorlds().get(0), 10, 55, 10));
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
		return new Location(Bukkit.getWorlds().get(0), 0, 150, 0);
	}
}
