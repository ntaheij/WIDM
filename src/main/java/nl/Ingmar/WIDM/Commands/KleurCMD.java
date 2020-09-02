package nl.Ingmar.WIDM.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.Ingmar.WIDM.Main;
import nl.Ingmar.WIDM.Utils.IngamePlayer;
import nl.Ingmar.WIDM.Utils.KleurType;
import nl.Ingmar.WIDM.Utils.PlayerType;

public class KleurCMD implements CommandExecutor 
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("kleur"))
		{
			if(!(sender instanceof Player)) 
			{
				System.out.println("[WIDM] Dit kan alleen door spelers gedaan worden.");
				return true;
			}
			
			Player p = (Player)sender;
			
			if(!p.hasPermission("widm.kleur"))
			{
				p.sendMessage("§6[WIDM] §cJe hebt hier geen permissie voor!");
				return true;
			}
			
			if(args.length < 2)
			{
				help(p, label, args);
				return true;
			}
			else if(args[0].equalsIgnoreCase("set"))
			{
				if(args.length < 3)
				{
					p.sendMessage("§6[WIDM] §cGebruik: /" + label + " set (Speler) (Kleur)");
				}
				else
				{
					set(p, label, args);
				}
			}
			else if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("reset"))
			{
				if(args.length < 2)
				{
					p.sendMessage("§6[WIDM] §cGebruik: /" + label + " " + args[0].toLowerCase() + " (Speler)");
				}
				else
				{
					remove(p, label, args);
				}
			}
			else if(args[0].equalsIgnoreCase("setrole") || args[0].equalsIgnoreCase("setrol"))
			{
				if(args.length < 3)
				{
					p.sendMessage("§6[WIDM] §cGebruik: /" + label + " " + args[0].toLowerCase() + " (Kleur) (Rol)");
				}
				else
				{
					setrole(p, label, args);
				}
			}
			else
			{
				help(p, label, args);
			}
		}
		return false;
	}
	
	public void help(Player p, String label, String[] args)
	{
		p.sendMessage("§6[WIDM] §e/" + label);
		p.sendMessage("§6[WIDM] §e/" + label + " set (Speler) (Kleur)");
		p.sendMessage("§6[WIDM] §e/" + label + " remove/reset (Speler)");
		p.sendMessage("§6[WIDM] §e/" + label + " setrole (Kleur) (Rol)");
	}

	public void set(Player p, String label, String[] args)
	{
		Player selected = Bukkit.getServer().getPlayerExact(args[1]);
		
		if(selected == null)
		{
			p.sendMessage("§6[WIDM] §cDeze speler is niet online!");
			return;
		}
		
		String kleur = args[2].toUpperCase();
		KleurType type = null;
		
		switch(kleur)
		{
			case "LICHT_BLAUW":
				type = KleurType.LICHT_BLAUW;
				break;
			case "LICHT_GROEN":
				type = KleurType.LICHT_GROEN;
				break;
			case "ROZE":
				type = KleurType.ROZE;
				break;
			case "GEEL":
				type = KleurType.GEEL;
				break;
			case "WIT":
				type = KleurType.WIT;
				break;
			case "BLAUW":
				type = KleurType.BLAUW;
				break;
			case "GROEN":
				type = KleurType.GROEN;
				break;
			case "AQUA":
				type = KleurType.AQUA;
				break;
			case "ROOD":
				type = KleurType.ROOD;
				break;
			case "PAARS":
				type = KleurType.PAARS;
				break;
			case "ORANJE":
				type = KleurType.ORANJE;
				break;
			case "GRIJS":
				type = KleurType.GRIJS;
				break;
		}

		if(type == null)
		{
			p.sendMessage("§6[WIDM] §cKies een van de volgende kleuren: LICHT_GROEN, LICHT_BLAUW, ROZE, GEEL, WIT, BLAUW, GROEN, AQUA, ROOD, PAARS, ORANJE, GRIJS.");
			return;
		}

		for(IngamePlayer ip : Main.activeplayers) 
		{
		    if(ip.getPlayer().equals(selected))
		    {
		    	Main.activeplayers.remove(ip);
		    }
		}

		for(IngamePlayer ip1 : Main.memberplayers)
		{
			if(ip1.getPlayer().equals(selected))
			{
				Main.memberplayers.remove(ip1);
			}
		}
		
		IngamePlayer ip = new IngamePlayer(selected, type);
		selected.setPlayerListName(type.getKleur() + selected.getName());
		Main.activeplayers.add(ip);
		Main.memberplayers.add(ip);

		p.sendMessage("§6[WIDM] §aDe kleur van §2" + selected.getName() + "§a is veranderd naar " + type.getKleur() +  type.getNaam() + "§a.");
	}

	public void setrole(Player p, String label, String[] args)
	{
		String kleur = args[1].toUpperCase();
		KleurType type = null;
		
		switch(kleur)
		{
			case "LICHT_BLAUW":
				type = KleurType.LICHT_BLAUW;
				break;
			case "LICHT_GROEN":
				type = KleurType.LICHT_GROEN;
				break;
			case "ROZE":
				type = KleurType.ROZE;
				break;
			case "GEEL":
				type = KleurType.GEEL;
				break;
			case "WIT":
				type = KleurType.WIT;
				break;
			case "BLAUW":
				type = KleurType.BLAUW;
				break;
			case "GROEN":
				type = KleurType.GROEN;
				break;
			case "AQUA":
				type = KleurType.AQUA;
				break;
			case "ROOD":
				type = KleurType.ROOD;
				break;
			case "PAARS":
				type = KleurType.PAARS;
				break;
			case "ORANJE":
				type = KleurType.ORANJE;
				break;
			case "GRIJS":
				type = KleurType.GRIJS;
				break;
		}
		if(type == null)
		{
			p.sendMessage("§6[WIDM] §cKies een van de volgende kleuren: LICHT_GROEN, LICHT_BLAUW, ROZE, GEEL, WIT, BLAUW, GROEN, AQUA, ROOD, PAARS, ORANJE, GRIJS.");
			return;
		}
		
		String rol = args[2].toUpperCase();
		PlayerType pt = null;
		
		switch(rol)
		{
			case "PLAYER":
				pt = PlayerType.PLAYER;
				break;
			case "MOL":
				pt = PlayerType.MOL;
				break;
			case "EGOIST":
				pt = PlayerType.EGOIST;
				break;
			case "ENGEL":
				pt = PlayerType.ENGEL;
				break;
			case "EXECUTIONER":
				pt = PlayerType.EXECUTIONER;
				break;
			
		}
		if(pt == null)
		{
			p.sendMessage("§6[WIDM] §cKies een van de volgende rollen: PLAYER, MOL, EGOIST, ENGEL of EXECUTIONER.");
			return;
		}	
		
		for(IngamePlayer ip : Main.activeplayers) 
		{
		    if(ip.getKleurType().equals(type))
		    {
		    	ip.setPlayerType(pt);
		    	p.sendMessage("§6[WIDM] §aDe rol van " + 
		    	type.getKleur() + type.getNaam() + " §a(" + type.getKleur() + ip.getPlayer().getName() +
		    	"§a) is succesvol veranderd naar §2" + pt.getNaam() + "§a.");
				return;
		    }
		}

		for(IngamePlayer ip : Main.memberplayers) 
		{
		    if(ip.getKleurType().equals(type))
		    {
		    	ip.setPlayerType(pt);
				return;
		    }
		}

		p.sendMessage("§6[WIDM] §cEr is nog geen speler ingedeeld bij de kleur " + 
		type.getKleur() + type.getNaam() + "§c.");
		//
		
	}

	public void remove(Player p, String label, String[] args)
	{
		Player selected = Bukkit.getServer().getPlayerExact(args[1]);
		
		if(selected == null)
		{
			p.sendMessage("§6[WIDM] §cDeze speler is niet online!");
			return;
		}
		
		for(IngamePlayer ip : Main.activeplayers) 
		{
		    if(ip.getPlayer().equals(selected))
		    {
		    	Main.activeplayers.remove(ip);
		    	
		    	selected.setPlayerListName(selected.getName());
				
				p.sendMessage("§6[WIDM] §aDe WIDM-data van §2" + selected.getName() + "§a is gereset.");
				return;
		    }
		}

		for(IngamePlayer ip : Main.memberplayers) 
		{
		    if(ip.getPlayer().equals(selected))
		    {
		    	Main.memberplayers.remove(ip);
		    	return;
		    }
		}

		p.sendMessage("§6[WIDM] §cDeze speler deed niet mee met WIDM!");
	}
}