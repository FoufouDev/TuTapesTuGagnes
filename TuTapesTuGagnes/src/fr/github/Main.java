package fr.github;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import fr.github.events.PlayerMainEvents;
import fr.github.game.State;
import fr.github.utils.TeleportUtils;

public class Main extends JavaPlugin {
	
	static Main instance;
	
	private TeleportUtils teleportUtils;
	
	private List<Player> players;
	
	private Map<Player, Integer> playerPoints;
	
	@Override
	public void onEnable() {
		instance = this; 
		State.setState(State.LOBBY);
		teleportUtils = new TeleportUtils();
		players = new ArrayList<>();
		playerPoints = new HashMap<>();
		registerListeners(new PlayerMainEvents(this));
		
		super.onEnable();
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}
	
	private void registerListeners(Listener... listeners){
		Arrays.stream(listeners).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
	}
	
	public TeleportUtils getTeleportUtils() {
		return teleportUtils;
	}
	
	public Map<Player, Integer> getPlayerPointsMap() {
		return playerPoints;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	public static Main getInstance() {
		return instance;
	}
}
