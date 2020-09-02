package nl.Ingmar.WIDM.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		if (	p.hasPermission("widm.admin") || 
				p.hasPermission("widm.kleur") || 
				p.hasPermission("widm.map") || 
				p.hasPermission("widm.start")
			)
		{
			p.sendMessage("§6[WIDM] §eWIDM Gemaakt door §cIngmar14000 & NTaheij.");
		}
	}
}
