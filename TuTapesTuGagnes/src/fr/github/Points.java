package fr.github;

import org.bukkit.entity.Player;

public abstract class Points {
	
	int points;
	protected Player p;
	
	public Points(Player p){
		this.p = p;
		this.points = Main.getInstance().getPlayerPointsMap().get(p);
	}
	
	public abstract int getPoints(Player p);
	public abstract void addPlayerPoint(Player p);
}
