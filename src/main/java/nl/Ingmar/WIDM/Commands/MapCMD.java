package nl.Ingmar.WIDM.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.Ingmar.WIDM.Main;
public class MapCMD implements CommandExecutor 
{
	private final Main main;
	
	public MapCMD(Main main)
	{
		this.main = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("map"))
		{
			if(!(sender instanceof Player)) 
			{
				System.out.println("[WIDM] Dit kan alleen door spelers gedaan worden.");
				return true;
			}
			
			Player p = (Player)sender;
			
			if(!p.hasPermission("widm.map"))
			{
				p.sendMessage("§6[WIDM] §cJe hebt hier geen permissie voor!");
				return true;
			}
			
			if(args.length < 1)
			{
				help(p, label, args);
				return true;
			}
			else if(args[0].equalsIgnoreCase("list"))
			{
					list(p, label, args);
			}
			else if(args[0].equalsIgnoreCase("select") || args[0].equalsIgnoreCase("selecteer"))
			{
				if(args.length < 2)
				{
					p.sendMessage("§6[WIDM] §cGebruik: /" + label + " " + args[0].toLowerCase() + " (Mapnaam)");
				}
				else
				{
					select(p, label, args);
				}
			}
			else if(args[0].equalsIgnoreCase("create"))
			{
				if(args.length < 2)
				{
					p.sendMessage("§6[WIDM] §cGebruik: /" + label + " create (Mapnaam)");
				}
				else
				{
					create(p, label, args);
				}
			}
			else if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete"))
			{
				if(args.length < 2)
				{
					p.sendMessage("§6[WIDM] §cGebruik: /" + label + " remove (Mapnaam)");
				}
				else
				{
					remove(p, label, args);
				}
			}
			else if(args[0].equalsIgnoreCase("setspawn"))
			{
				if(args.length < 2)
				{
					p.sendMessage("§6[WIDM] §cGebruik: /" + label + " setspawn (Mapnaam)");
				}
				else
				{
					setSpawn(p, label, args);
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
		p.sendMessage("§6[WIDM] §e/" + label + " list");
		p.sendMessage("§6[WIDM] §e/" + label + " select (Mapnaam)");
		p.sendMessage("§6[WIDM] §e/" + label + " create (Mapnaam)");
		p.sendMessage("§6[WIDM] §e/" + label + " remove (Mapnaam)");
		p.sendMessage("§6[WIDM] §e/" + label + " setspawn (Mapnaam)");
	}
	
	public void list(Player p, String label, String[] args)
	{
		if (main.getConfig().getConfigurationSection("maps") == null ||
			main.getConfig().getConfigurationSection("maps").getKeys(false).isEmpty())
		{
			p.sendMessage("§6[WIDM] §cEr zijn nog geen mappen." );
			return;
		}
		p.sendMessage("§6WIDM mappen");
		for(String path : main.getConfig().getConfigurationSection("maps").getKeys(false))
		{
			p.sendMessage("§6- " + path);
		}
	}
	
	public void select(Player p, String label, String[] args)
	{
		if(main.getConfig().getString("maps." + args[1].toLowerCase() + ".enabled") != null)
		{
			Main.currentMap = args[1].toLowerCase();
			p.sendMessage("§6[WIDM] §aSuccesvol de gekozen map veranderd naar §2" + args[1].toLowerCase() + "§a.");
		}
		else
		{
			p.sendMessage("§6[WIDM] §cDeze map bestaat niet!");
		}
	}
	
	public void create(Player p, String label, String[] args)
	{
		if(main.getConfig().getString("maps." + args[1].toLowerCase() + ".enabled") != null)
		{
			p.sendMessage("§6[WIDM] §cDe map " + args[1].toLowerCase() + " bestaat al!");
		}
		else
		{
			main.getConfig().set("maps." + args[1].toLowerCase() + ".enabled", "ja");
			main.getConfig().set("maps." + args[1].toLowerCase() + 
					".location.x", Math.round(p.getLocation().getX()));
			main.getConfig().set("maps." + args[1].toLowerCase() + 
					".location.y", Math.round(p.getLocation().getY()));
			main.getConfig().set("maps." + args[1].toLowerCase() + 
					".location.z", Math.round(p.getLocation().getZ()));
			main.getConfig().set("maps." + args[1].toLowerCase() + 
					".location.world", p.getLocation().getWorld().getName().toString());
			
			p.sendMessage("§6[WIDM] §aSuccesvol de map " + args[1].toLowerCase() + " aangemaakt op deze locatie.");
			p.sendMessage("§6[WIDM] Om de locatie aan te passen, doe /map setspawn " + args[1].toLowerCase());
		}
	}
	
	public void remove(Player p, String label, String[] args)
	{
		if(main.getConfig().getString("maps." + args[1].toLowerCase() + ".enabled") != null)
		{
			main.getConfig().set("maps." + args[1].toLowerCase(), null);
			main.getConfig().set("maps." + args[1].toLowerCase() + ".enabled", null);
			main.getConfig().set("maps." + args[1].toLowerCase() + 
					".location.x", null);
			main.getConfig().set("maps." + args[1].toLowerCase() + 
					".location.y", null);
			main.getConfig().set("maps." + args[1].toLowerCase() + 
					".location.z", null);
			main.getConfig().set("maps." + args[1].toLowerCase() + 
					".location.world", null);
			
			main.saveConfig();
			
			p.sendMessage("§6[WIDM] §aSuccesvol de map " + args[1].toLowerCase() + " verwijderd.");
		}
		else
		{
			p.sendMessage("§6[WIDM] §cDe map " + args[1].toLowerCase() + " bestaat niet!");
		}
	}
	
	public void setSpawn(Player p, String label, String[] args)
	{
		if(main.getConfig().getString("maps." + args[1].toLowerCase() + ".enabled") != null)
		{
			main.getConfig().set("maps." + args[1].toLowerCase() + ".enabled", "ja");
			main.getConfig().set("maps." + args[1].toLowerCase() + 
					".location.x", Math.round(p.getLocation().getX()));
			main.getConfig().set("maps." + args[1].toLowerCase() + 
					".location.y", Math.round(p.getLocation().getY()));
			main.getConfig().set("maps." + args[1].toLowerCase() + 
					".location.z", Math.round(p.getLocation().getZ()));
			main.getConfig().set("maps." + args[1].toLowerCase() + 
					".location.world", p.getLocation().getWorld().getName().toString());
			
			main.saveConfig();
			
			p.sendMessage("§6[WIDM] §aSuccesvol de locatie van de map " + args[1].toLowerCase() + " aangepast.");
		}
		else
		{
			p.sendMessage("§6[WIDM] §cDe map " + args[1].toLowerCase() + " bestaat niet!");
		}
	}
}
