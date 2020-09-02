package nl.Ingmar.WIDM.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import nl.Ingmar.WIDM.Main;
import nl.Ingmar.WIDM.Utils.IngamePlayer;

public class ChatListener implements Listener
{
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer();
		
		for(IngamePlayer ip : Main.activeplayers) 
		{
		    if(ip.getPlayer().equals(p))
		    {
	    		e.setCancelled(true);
		    	for(IngamePlayer chatters : Main.activeplayers) 
		    	{
		    		chatters.getPlayer().sendMessage("§6[WIDM] " + 
		    		ip.getKleurType().getKleur() + ip.getPlayer().getName() + "§f: " +
		    		e.getMessage());
		    	}
		    }
		}
	}
}