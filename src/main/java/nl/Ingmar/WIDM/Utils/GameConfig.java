package nl.Ingmar.WIDM.Utils;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nl.Ingmar.WIDM.Main;

public class GameConfig {
	
	private static Main main;
	
	public GameConfig(Main main)
	{
		GameConfig.main = main;
	}
 
    static File cfile;
    static FileConfiguration config;
    static File df = main.getDataFolder();
    static File folder = new File(df, "gamedata" + File.separator);
 
    public static void create(String s) {
        cfile = new File(df, "gamedata" + File.separator + s + ".yml");
        if (!df.exists()) df.mkdir();
        if (!cfile.exists()) {
            try {
                cfile.createNewFile();
            } catch(Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error creating " + cfile.getName() + "!");
            }
        }
        config = YamlConfiguration.loadConfiguration(cfile);
    }
 
    public static File getFolder() {
        return folder;
    }
 
    public static File getFile() {
        return cfile;
    }
 
    public static void load(String s) {
        cfile = new File(df, "gamedata" + File.separator + s + ".yml");
        config = YamlConfiguration.loadConfiguration(cfile);
    }
 
    public static FileConfiguration get() 
    {
        return config;
    }
 
    public static void save() {
        try {
            config.save(cfile);
        } catch(Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error saving " + cfile.getName() + "!");
        }
    }
}
