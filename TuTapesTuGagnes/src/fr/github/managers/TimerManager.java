package fr.github.managers;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.github.TuTapesTuGagnes;
import fr.github.game.State;

public class TimerManager {
	private int timer = 5;
	private String prefix = ChatColor.translateAlternateColorCodes('&', "&cTuTapesTuGagnes &b» &a");
	
	private HashMap playersStats = new HashMap();
	TreeMap treeMap = new TreeMap(new Comp(playersStats));
	
	public void startGame(){
		State.setState(State.GAME);
	Bukkit.getScheduler().scheduleSyncRepeatingTask(TuTapesTuGagnes.getInstance(), new Runnable() {
		
		@Override
		public void run() {
			if(timer <= 5 && timer > 1){
				Bukkit.broadcastMessage(prefix + "La partie fini dans "+timer+" minutes !");
			}
			if(timer == 1){
				Bukkit.broadcastMessage(prefix+"La partie se fini dans "+ timer +" minute !");
			}
			if(timer == 0){
				for(Player online : TuTapesTuGagnes.getInstance().getPlayers()){
					playersStats.put(online.getName(), online.getLevel());
				}
				treeMap.putAll(playersStats);

				Bukkit.broadcastMessage(prefix + "La partie est fini ! Bravo à "+ treeMap.firstEntry().getKey() +" pour avoir gagner avec "+treeMap.firstEntry().getValue() +" points");
				// Affichera Bravo à %pseudo% d'avoir gagner avec %points%
			}
			
			--timer;
		}
	}, 0, 20 * 60); // toutes les minutes
	}
	
}
	class Comp implements Comparator{
		
		Map map;
		
		public Comp(HashMap map){
			this.map = map;
		}
		
		@Override
		public int compare(Object obj1, Object obj2) {
			if((int) map.get(obj1) >= (int) map.get(obj2)){
				return -1;
			} else {
				return 1;
			}
		}
		
	
}
