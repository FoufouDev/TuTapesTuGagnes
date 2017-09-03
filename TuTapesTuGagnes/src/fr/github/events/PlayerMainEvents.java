package fr.github.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.github.TuTapesTuGagnes;
import fr.github.Points;
import fr.github.game.State;
import fr.github.managers.PlayerManagement;
import fr.github.managers.TimerManager;
import fr.github.utils.GamePlayer;

public class PlayerMainEvents implements Listener{
	
	private TuTapesTuGagnes main;
	
	private String prefix = ChatColor.translateAlternateColorCodes('&', "&cTuTapesTuGagnes &b» &a"); 
	private Points pointsManager;
	
	public PlayerMainEvents(TuTapesTuGagnes main) {
	this.main = main; 
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(State.isState(State.LOBBY)){
			
		main.getPlayers().add(p);
		p.setLevel(0);
		pointsManager = new PlayerManagement(p); // On peut pas déclarer une classe abstraite
		
		System.out.println(pointsManager.getPoints(p));
		pointsManager.addPlayerPoint(p);
		System.out.println(pointsManager.getPoints(p));
		
		e.setJoinMessage(prefix+ p.getName()+ " a rejoint la partie ! (" + main.getPlayers().size() + "/5)");
		
		p.getActivePotionEffects().forEach(potionEffect -> p.removePotionEffect(potionEffect.getType()));
		p.setFoodLevel(20); p.setHealth(20);
		
		GamePlayer player = new GamePlayer(p);
		player.setSpawnLocation(main.getTeleportUtils().getRandomSpawn());
		p.teleport(player.getSpawnLocation());
		
		if(Bukkit.getOnlinePlayers().size() >= 2 && State.isState(State.LOBBY)){
			new TimerManager().startGame();
		}
		
		} else {
			e.setJoinMessage(null);
			p.setGameMode(GameMode.SPECTATOR);
			p.sendMessage(prefix+ "La partie a déjà commencer");
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		if(State.isState(State.LOBBY)){
		main.getPlayers().remove(p != null ? true : false);  // eviter les NPE si ça contient pas^
		
		e.setQuitMessage(prefix + p.getName() + " a quitté la partie ! (" + main.getPlayers().size() + "/5)");
		} else {
			e.setQuitMessage(null);
		}
	}
	
	@EventHandler
	public void onDamageByEntity(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
			Player damaged = (Player) e.getEntity();
			Player damager = (Player) e.getDamager();
					pointsManager.addPlayerPoint(damager);
					Bukkit.broadcastMessage(prefix + damager.getName() + " a remporté 1 point en tapant "+damaged.getName()+" ! Il a "+pointsManager.getPoints(damager)+" points");
					damaged.setHealth(20); // pour pas qu'il perde de vie au ca où 
		}
	}
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		if(e.getPlayer().getLocation().getY() <= 50){
			e.getPlayer().teleport(new GamePlayer(e.getPlayer()).getSpawnLocation());
		}
	}
}
