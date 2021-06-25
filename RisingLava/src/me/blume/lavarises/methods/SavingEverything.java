package me.blume.lavarises.methods;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.blume.lavarises.Main;

public class SavingEverything {
	public void keepInventories() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(Main.players.contains(p.getUniqueId())) {
				Main.inventories.put(p.getUniqueId(), p.getInventory().getContents());
				p.getInventory().clear();
			}
		}
	}
	public void giveInventories() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(Main.players.contains(p.getUniqueId())) {
				p.getInventory().clear();
				ItemStack[] inv = Main.inventories.get(p.getUniqueId());
				p.getInventory().setContents(inv);
			}
		}
	}
	public void saveLevels() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(Main.players.contains(p.getUniqueId())) {
				Main.exp.put(p.getUniqueId(), p.getExp());
				Main.levels.put(p.getUniqueId(), p.getLevel());
				p.setLevel(0);
				p.setExp(0);
			}
		}
	}
	public void giveLevels() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(Main.players.contains(p.getUniqueId())) {
				p.setLevel(Main.levels.get(p.getUniqueId()));
				p.setExp(Main.exp.get(p.getUniqueId()));
			}
		}
	}
	public void saveHealth() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(Main.players.contains(p.getUniqueId())) {
				Main.health.put(p.getUniqueId(), p.getHealth());
				Main.food.put(p.getUniqueId(), p.getFoodLevel());
				p.setHealth(20);
				p.setFoodLevel(30);
			}
		}
	}
	public void giveHealth() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(Main.players.contains(p.getUniqueId())) {
				p.setHealth(Main.health.get(p.getUniqueId()));
				p.setFoodLevel(Main.food.get(p.getUniqueId()));
			}
		}
	}
	public void saveLocations(Player player) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(Main.players.contains(p.getUniqueId())) {
				Main.locationOfPlayers.put(p.getUniqueId(), p.getLocation());
				p.teleport(player.getLocation());
			}
		}
	}
	public void giveLocations() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(Main.players.contains(p.getUniqueId())) {
				p.teleport(Main.locationOfPlayers.get(p.getUniqueId()));
			}
		}
	}
	public void saveBlocks(Player p) {
		for(double x = ((Main.bordersize/2 * -1)+p.getLocation().getX());x<=((Main.bordersize / 2)+p.getLocation().getX());x++) {
			for(double z = ((Main.bordersize/2 * -1)+p.getLocation().getZ());z<=((Main.bordersize / 2)+p.getLocation().getZ());z++) {
				for(int y = 1;y<=256;y++) {
					Location blocksLocation = new Location(p.getWorld(),x,y,z);
					if(blocksLocation.getBlock().getType()==null) continue;
					Main.blocks.put(blocksLocation, blocksLocation.getBlock().getType());
				}
			}
		}
		World world = p.getWorld();
		List<Entity> entList = world.getEntities();
        for(Entity current : entList){
            if (current.getType() == EntityType.DROPPED_ITEM){
            	current.remove();
            }
        }
	}
	public void giveBlocks(Player p) {
		for(double x = ((Main.bordersize/2 * -1)+p.getLocation().getX());x<=((Main.bordersize / 2)+p.getLocation().getX());x++) {
			for(double z = ((Main.bordersize/2 * -1)+p.getLocation().getZ());z<=((Main.bordersize / 2)+p.getLocation().getZ());z++) {
				for(int y = 1;y<=heightMax(p);y++) {
					Location blocksLocation = new Location(p.getWorld(),x,y,z);
					if(blocksLocation.getBlock().getType()==null) continue;
					if(blocksLocation.getBlock().getType().equals(Main.blocks.get(blocksLocation))) continue;
					blocksLocation.getBlock().setType(Main.blocks.get(blocksLocation));
				}
			}
		}
		World world = p.getWorld();
		List<Entity> entList = world.getEntities();
        for(Entity current : entList){
            if (current.getType() == EntityType.DROPPED_ITEM){
            	current.remove();
            }
        }
	}
	public int heightMax(Player p) {
		for(int y = 256;y>=1;y--) {
		for(double x = ((Main.bordersize/2 * -1)+p.getLocation().getX());x<=((Main.bordersize / 2)+p.getLocation().getX());x++) {
			for(double z = ((Main.bordersize/2 * -1)+p.getLocation().getZ());z<=((Main.bordersize / 2)+p.getLocation().getZ());z++) {
				
					Location blocksLocation = new Location(p.getWorld(),x,y,z);
					if(blocksLocation.getBlock().getType()==Material.AIR) continue;
					else {
						return y;
					}
				}
			}
		}
		return 256;
	}

}
