package fr.github.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.github.Main;
import fr.github.Points;
import fr.github.game.State;
import fr.github.managers.PlayerManagement;

public class PlayerMainEvents implements Listener{
	
	private Main main;
	
	private String prefix = ChatColor.translateAlternateColorCodes('&', "&cTuTapesTuGagnes &b» &a"); 
	private Points pointsManager;
	
	public PlayerMainEvents(Main main) {
	this.main = main; 
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(State.isState(State.LOBBY)){
			
		main.getPlayers().add(p);
		main.getPlayerPointsMap().put(p, 0);
		
		pointsManager = new PlayerManagement(p); // On peut pas déclarer une classe abstraite
		
		e.setJoinMessage(prefix+ p.getName()+ " a rejoint la partie ! (" + main.getPlayers().size() + "/5)");
		
		p.getActivePotionEffects().forEach(potionEffect -> p.removePotionEffect(potionEffect.getType()));
		p.setFoodLevel(20); p.setHealth(20);
		
		p.teleport(main.getTeleportUtils().getSpawnLocation());
		
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
		main.getPlayerPointsMap().remove(p, main.getPlayerPointsMap().get(p));
		
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
		}
	}
}
