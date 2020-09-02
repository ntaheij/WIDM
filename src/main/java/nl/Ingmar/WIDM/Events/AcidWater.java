package nl.Ingmar.WIDM.Events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import nl.Ingmar.WIDM.Main;
import nl.Ingmar.WIDM.Utils.IngamePlayer;

public class AcidWater implements Listener
{
	private final Main main;
	
	public BukkitTask runb;
	
	public AcidWater(Main pl)
	{
		main = pl;
	}
	
	public void StartAcidWater() 
	{
		Main.acidWaterOn = true;
		
		runb = new BukkitRunnable()
		{
			@Override
			public void run()
			{
				if(!Main.acidWaterOn)
				{
					return;
				}
				if (Main.activeplayers.size() == 0) {
					return;
				}

				int damage = main.getConfig().getInt("acid-water-damage");

				for(Player p : Bukkit.getOnlinePlayers())
				{
					boolean bln = true;
					for(IngamePlayer ip : Main.activeplayers)
					{
						if(ip.getPlayer().equals(p))
						{
							bln = false;
						}
					}
					if(bln)
					{
						break;
					}
					else
					{
						if(p.getLocation().getBlock().getType().equals(Material.WATER) || p.getLocation().getBlock().getType().equals(Material.STATIONARY_WATER))
						{
							if(!p.isInsideVehicle())
							{
								if(p.getGameMode().equals(GameMode.SURVIVAL))
								{
									if(!p.hasPotionEffect(PotionEffectType.WATER_BREATHING))
									{
										if(p.getHealth() > damage + 1)
										{
											p.setHealth(p.getHealth() - damage);
										}
										else
										{
											p.setHealth(0);
										}
									}
								}
							}
						}
					}
				}
			}
		}.runTaskTimer(main, 0L, 10L);
	}

	public void EndAcidWater()
	{
		Main.acidWaterOn = false;

		if (runb != null) 
		{
			runb.cancel();
		}
	}
}
