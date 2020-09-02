package nl.Ingmar.WIDM.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import nl.Ingmar.WIDM.Main;
import nl.Ingmar.WIDM.Utils.IngamePlayer;

public class DeathListener implements Listener 
{
	@EventHandler
	public void OnDeath(PlayerDeathEvent e)
	{
		Player p = e.getEntity();

		for(IngamePlayer ip : Main.activeplayers) 
		{
		    if(ip.getPlayer().equals(p))
		    {
		    	Main.activeplayers.remove(ip);
				break;
		    }
		}
	}
}
