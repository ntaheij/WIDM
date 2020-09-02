package nl.Ingmar.WIDM.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import nl.Ingmar.WIDM.Main;
import nl.Ingmar.WIDM.Utils.IngamePlayer;

public class RespawnListener implements Listener 
{
	@EventHandler
	public void OnRespawn(PlayerRespawnEvent e)
	{
		Player p = e.getPlayer();

		for(IngamePlayer ip : Main.memberplayers) 
		{
		    if(ip.getPlayer().equals(p))
		    {
		    	Main.activeplayers.add(ip);
				break;
		    }
		}
	}
}