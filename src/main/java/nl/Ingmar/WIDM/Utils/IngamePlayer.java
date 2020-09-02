package nl.Ingmar.WIDM.Utils;

import org.bukkit.entity.Player;

public class IngamePlayer 
{
	Player p;
	KleurType kt;
	PlayerType pt;
	
	public IngamePlayer(Player p, KleurType type)
	{
		this.p = p;
		this.kt = type;
	}

	public PlayerType getPlayerType() {
		return pt;
	}

	public void setPlayerType(PlayerType pt) {
		this.pt = pt;
	}
	
	public KleurType getKleurType() {
		return kt;
	}

	public void setKleurType(KleurType kt) {
		this.kt = kt;
	}
	
	public Player getPlayer() {
		return p;
	}
}
