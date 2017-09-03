package fr.github.managers;

import org.bukkit.entity.Player;

import fr.github.Main;
import fr.github.Points;

public class PlayerManagement extends Points {

	public PlayerManagement(Player p) {
		super(p);
	}

	@Override
	public int getPoints(Player p) {
		// TODO Auto-generated method stub
		return Main.getInstance().getPlayerPointsMap().get(p);
	}

	@Override
	public void addPlayerPoint(Player p) {
		Main.getInstance().getPlayerPointsMap().put(p, Main.getInstance().getPlayerPointsMap().get(p)+1);
	}

}
