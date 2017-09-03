package fr.github.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GamePlayer {
	
	private Player p;
	private int points;
	private Location spawnLocation;
	
	public GamePlayer(Player p) {
		this.p = p;
		points = p.getLevel(); 
	}
	public Player getPlayer(){
		return p;
	}
	public int getPoints() {
		return points;
	}
	public void addPoints(){
		points = points+1;
		p.setLevel(points);
	}
	public void setSpawnLocation(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
	}
	public Location getSpawnLocation() {
		return spawnLocation;
	}
}
