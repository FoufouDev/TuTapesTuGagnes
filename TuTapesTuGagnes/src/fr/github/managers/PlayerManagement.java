package fr.github.managers;

import org.bukkit.entity.Player;

import fr.github.Points;
import fr.github.utils.GamePlayer;

public class PlayerManagement extends Points {

	public PlayerManagement(Player p) {
		super(p);
	}

	@Override
	public int getPoints(Player p) {
		GamePlayer gPlayer = new GamePlayer(p);
		return gPlayer.getPoints();
	}

	@Override
	public void addPlayerPoint(Player p) {
		GamePlayer gamePlayer = new GamePlayer(p);
		gamePlayer.addPoints();
		p.setLevel(gamePlayer.getPoints());
	}

}
