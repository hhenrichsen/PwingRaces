package net.pwing.races.utilities;

import org.bukkit.Bukkit;

public class VersionUtil {

	public static String getBukkitVersion() {
		return Bukkit.getServer().getBukkitVersion();
	}
	
	public static String getNMSPackage() {
		return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	}
}
