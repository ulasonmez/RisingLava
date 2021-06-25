package me.blume.lavarises.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.blume.lavarises.Main;
import me.blume.lavarises.methods.AllMethods;

public class LavaRisesStop implements CommandExecutor{

	@SuppressWarnings("unused")
	private Main plugin;
	public LavaRisesStop(Main plugin) {
		this.plugin=plugin;
	}
	AllMethods am = new AllMethods();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.isOp()) {
				if(label.equals("stoplavarises")) {
					WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
					Location centerWorld = new Location(player.getWorld(),0,0,0);
					wb.setCenter(centerWorld);
					wb.setSize(30000000);
					Main.task.cancel();
					am.stopLavaRises(player);
				}
			}
		}
		return false;
	}
}
