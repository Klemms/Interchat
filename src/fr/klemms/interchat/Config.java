package fr.klemms.interchat;

import org.bukkit.plugin.java.JavaPlugin;

public class Config {
	
	public static int pluginVersion = 1;

	public static void registerConfig(JavaPlugin plugin) {
		plugin.getConfig().addDefault("pluginVersion", pluginVersion);
		
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
	}
}
