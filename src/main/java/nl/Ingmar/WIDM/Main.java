package nl.Ingmar.WIDM;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import dev.ntaheij.Licensing.License;
import dev.ntaheij.Licensing.License.ValidationType;
import nl.Ingmar.WIDM.Commands.KleurCMD;
import nl.Ingmar.WIDM.Commands.MapCMD;
import nl.Ingmar.WIDM.Commands.StartCMD;
import nl.Ingmar.WIDM.Commands.WIDMCMD;
import nl.Ingmar.WIDM.Events.AcidWater;
import nl.Ingmar.WIDM.Events.BlockPlace;
import nl.Ingmar.WIDM.Events.ChatListener;
import nl.Ingmar.WIDM.Events.DeathListener;
import nl.Ingmar.WIDM.Events.JoinListener;
import nl.Ingmar.WIDM.Events.RespawnListener;
import nl.Ingmar.WIDM.Utils.IngamePlayer;

public class Main extends JavaPlugin
{

	public static String currentMap = "";

	public static ArrayList<IngamePlayer> activeplayers = new ArrayList<IngamePlayer>();
	public static ArrayList<IngamePlayer> memberplayers = new ArrayList<IngamePlayer>(); 
	
	public static boolean inGame = false;
	public static boolean acidWaterOn = false;
	public AcidWater acidWaterClass;
	
	public void onEnable()
	{
		getCommand("kleur").setExecutor(new KleurCMD());
		getCommand("map").setExecutor(new MapCMD(this));
		getCommand("start").setExecutor(new StartCMD(this));
		getCommand("WIDM").setExecutor(new WIDMCMD(this));
		
		getServer().getPluginManager().registerEvents(new BlockPlace(), this);
		getServer().getPluginManager().registerEvents(new ChatListener(), this);
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getServer().getPluginManager().registerEvents(new DeathListener(), this);
		getServer().getPluginManager().registerEvents(new RespawnListener(), this);
		
		saveDefaultConfig();
		
		acidWaterClass = new AcidWater(this);
		
		validate();
		
		System.out.println("[WIDM] is succesvol opgestart.");
		
	}
	
	public void onDisable()
	{
		reset();
		saveConfig();
		
		acidWaterClass.EndAcidWater();
		
		System.out.println("[WIDM] is succesvol afgesloten.");
	}
	
	public static void reset()
	{
		for(Player p : Bukkit.getOnlinePlayers()) 
		{
				p.getPlayer().setPlayerListName(p.getName());		
		}
		
		
		activeplayers.clear();
		memberplayers.clear();
		inGame = false;
		acidWaterOn = false;
	}
	
	public AcidWater GetAcidWaterClass()
	{
		if (this.acidWaterClass == null)
		{
			this.acidWaterClass = new AcidWater(this);
		}
		return this.acidWaterClass;
	}
	
	private void validate()
	{
		String license = getConfig().getString("license") != null ? getConfig().getString("license") : "WIDM-L840-OC3D-9R6Z"; 
		
		System.out.println("[!] NTaheij.DEV Licensing System");
		System.out.println("[!] Checking key: " + license);
		System.out.println("[!] For plugin: " + this.getName());

		ValidationType vt = new License(license, this).isValid();

		if(vt == ValidationType.VALID)
		{
			System.out.println("[!] License is valid!");
		}
		else
		{
			
			System.out.println("[!] License is invalid!");
			if(vt == ValidationType.INVALID_PLUGIN)
			{
				System.out.println("[!] Reason: This license is not for this plugin!");
			}
			if(vt == ValidationType.KEY_NOT_FOUND)
			{
				System.out.println("[!] Reason: This license does not exist or has expired!");
			}
			if(vt == ValidationType.KEY_OUTDATED)
			{
				System.out.println("[!] Reason: The key has expired, please request a new one!");
			}
			if(vt == ValidationType.NOT_VALID_IP)
			{
				System.out.println("[!] Reason: The maximum IPs on this key has been reached!");
			}
			System.out.println("[!] If you think something is wrong, please contact me with code: " + vt.toString() + "#" + License.xor(License.toBinary(vt.toString()).toString(), vt.toString()));
			
			Bukkit.getScheduler().cancelTasks(this);
			Bukkit.getPluginManager().disablePlugin(this);
		}
	}
}
