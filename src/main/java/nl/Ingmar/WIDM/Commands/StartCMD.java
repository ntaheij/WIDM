package nl.Ingmar.WIDM.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.Ingmar.WIDM.Main;
import nl.Ingmar.WIDM.Utils.IngamePlayer;

public class StartCMD implements CommandExecutor 
{
	
	private final Main main;
		
	public StartCMD(Main main)
	{
		this.main = main;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("start"))
		{
			if(!(sender instanceof Player)) 
			{
				System.out.println("[WIDM] Dit kan alleen door spelers gedaan worden.");
				return true;
			}
			
			Player p = (Player)sender;
			
			if(!p.hasPermission("widm.start") && !p.hasPermission("widm.admin"))
			{
				p.sendMessage("§6[WIDM] §cJe hebt hier geen permissie voor!");
				return true;
			}
			else
			{
				if (Main.inGame)
				{
					p.sendMessage("§6[WIDM] §cEr is reeds een spel gestart.");
					return true;
				}

				if(Main.currentMap.equals("") || Main.currentMap == null)
				{
					p.sendMessage("§6[WIDM] §cEr is nog geen map geselecteerd! Selecteer deze met /map select (Mapnaam).");
					return true;
				}
				
				if(Main.activeplayers.size() < 3)
				{
					p.sendMessage("§6[WIDM] §cEr zijn te weinig spelers om het spel te starten! (" + Main.activeplayers.size() + ")");
					return true;
				}
				
				//Date d = new Date();
				
				//String datum = new SimpleDateFormat("yyyy-MM-dd-h:mm-a:zzzz").format(d);
				
				////GameConfig.create(datum);
				
				//GameConfig.get().set("gamedata." + datum + ".date.year", d.getYear());
				//GameConfig.get().set("gamedata." + datum + ".date.month", d.getMonth());
				//GameConfig.get().set("gamedata." + datum + ".date.day", d.getDay());
				//GameConfig.get().set("gamedata." + datum + ".date.hour", d.getHours());
				//GameConfig.get().set("gamedata." + datum + ".date.minutes", d.getMinutes());
				//GameConfig.get().set("gamedata." + datum + ".date.timezone", d.getTimezoneOffset());
				
				//GameConfig.get().set("gamedata." + datum + ".map", Main.currentMap);
				
				for(IngamePlayer ip : Main.activeplayers)
				{
					double x, y, z;
					World w;
					
					x = main.getConfig().getDouble("maps." + Main.currentMap.toLowerCase() + ".location.x");
					y = main.getConfig().getDouble("maps." + Main.currentMap.toLowerCase() + ".location.y");
					z = main.getConfig().getDouble("maps." + Main.currentMap.toLowerCase() + ".location.z");
					w = Bukkit.getWorld(main.getConfig().getString("maps." + Main.currentMap.toLowerCase() + ".location.world"));
					
					Location loc = new Location(w,x,y,z);
					
					ip.getPlayer().teleport(loc);
					
					ip.getPlayer().sendMessage("§6[WIDM] §aDe game gaat nu starten op de map §2" + Main.currentMap.toLowerCase() + "§a.");
					
					ip.getPlayer().sendMessage("§6Jouw WIDM informatie:");
					ip.getPlayer().sendMessage("§6Kleur: " + ip.getKleurType().getKleur() + ip.getKleurType().getNaam());
					
					if(main.getConfig().getBoolean("announce-role-on-start")) {
						ip.getPlayer().sendMessage("§6Rol: §c" + ip.getPlayerType().getNaam()); } else {
						ip.getPlayer().sendMessage("§6Rol: §cHidden");
					}
					
					ip.getPlayer().sendMessage("§aSucces!");
					
					//GameConfig.get().set("gamedata." + datum + ".player." + ip.getPlayer().getUniqueId() + ".naam", ip.getPlayer().getName());
					//GameConfig.get().set("gamedata." + datum + ".player." + ip.getPlayer().getUniqueId() + ".rol", ip.getPlayerType().getNaam());
					//GameConfig.get().set("gamedata." + datum + ".player." + ip.getPlayer().getUniqueId() + ".kleur", ip.getKleurType().getNaam());
				}
				
				//GameConfig.save();
				
				Main.inGame = true;
			}
		}
		return false;
	}

}
