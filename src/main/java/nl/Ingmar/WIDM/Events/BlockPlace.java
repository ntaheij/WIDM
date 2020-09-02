package nl.Ingmar.WIDM.Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import nl.Ingmar.WIDM.Main;

public class BlockPlace implements Listener
{
	@EventHandler
	public void onPlace(BlockPlaceEvent e)
	{
		Block b = e.getBlock();
		Material m = b.getType();

		if(b.getLocation().subtract(0, 1, 0).getBlock().getType().equals(Material.ENDER_STONE))
		{
			if(m.equals(Material.DIAMOND_BLOCK))
			{
				for(Player all : Bukkit.getServer().getOnlinePlayers())
				{
						all.getPlayer().sendMessage("§6[WIDM] §cDe mollen hebben gewonnen.");
				}
				Main.reset();
			}
			else if(m.equals(Material.GOLD_BLOCK))
			{
				for(Player all : Bukkit.getServer().getOnlinePlayers())
				{
						all.getPlayer().sendMessage("§6[WIDM] §cDe spelers hebben gewonnen.");
				}
				Main.reset();
			}
		}
	}
}
