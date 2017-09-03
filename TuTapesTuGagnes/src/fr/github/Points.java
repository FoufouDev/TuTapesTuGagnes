package fr.github;

import org.bukkit.entity.Player;

public abstract class Points {
	
	protected Player p;
	
	public Points(Player p){
		this.p = p;
	}
	
	public abstract int getPoints(Player p);
	public abstract void addPlayerPoint(Player p);
}
