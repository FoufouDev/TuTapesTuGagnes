package fr.github;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import fr.github.events.PlayerMainEvents;
import fr.github.game.State;
import fr.github.utils.TeleportUtils;

public class TuTapesTuGagnes extends JavaPlugin {
	
	static TuTapesTuGagnes instance;
	
	private TeleportUtils teleportUtils;
	
	private List<Player> players;
	
	@Override
	public void onEnable() {
		instance = this; 
		State.setState(State.LOBBY);
		teleportUtils = new TeleportUtils();
		players = new ArrayList<>();
		registerListeners(new PlayerMainEvents(this));
	}

	@Override
	public void onDisable() {}
	
	private void registerListeners(Listener... listeners){
		Arrays.stream(listeners).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
	}
	
	public TeleportUtils getTeleportUtils() {
		return teleportUtils;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	public static TuTapesTuGagnes getInstance() {
		return instance;
	}
}
