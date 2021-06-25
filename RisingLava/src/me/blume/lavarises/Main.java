package me.blume.lavarises;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import me.blume.lavarises.commands.LavaRisesStart;
import me.blume.lavarises.commands.LavaRisesStop;

public class Main extends JavaPlugin{
	public static int bordersize,lavaRiseTime;
	public static HashSet<UUID> players = new HashSet<UUID>();
	public static HashMap<UUID,ItemStack[]> inventories = new HashMap<UUID,ItemStack[]>();
	public static HashMap<UUID,Integer> levels = new HashMap<UUID,Integer>();
	public static HashMap<UUID,Float> exp = new HashMap<UUID,Float>();
	public static HashMap<UUID,Double> health = new HashMap<UUID,Double>();
	public static HashMap<UUID,Integer> food = new HashMap<UUID,Integer>();
	public static HashMap<Location,Material> blocks = new HashMap<Location,Material>();
	public static HashMap<UUID,Location> locationOfPlayers = new HashMap<UUID,Location>();
	public static BukkitTask task;

	@Override
	public void onEnable() {
		getCommand("lavarises").setExecutor(new LavaRisesStart(this));
		getCommand("stoplavarises").setExecutor(new LavaRisesStop(this));
	}
	
}
