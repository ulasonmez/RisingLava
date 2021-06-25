package me.blume.lavarises.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.blume.lavarises.Main;
import me.blume.lavarises.methods.AllMethods;

public class LavaRisesStart implements CommandExecutor{

	@SuppressWarnings("unused")
	private Main plugin;
	public LavaRisesStart(Main plugin) {
		this.plugin=plugin;
	}
	AllMethods am = new AllMethods();
	int bordersize,lavaRiseTime,y=1;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.isOp()) {
				if(label.equals("lavarises")) {
					if(args.length==2) {
						Main.bordersize = Integer.parseInt(args[0]);
						bordersize = Main.bordersize;
						if(bordersize>10 && bordersize<200) {
							Main.lavaRiseTime = Integer.parseInt(args[1]);
							lavaRiseTime = Main.lavaRiseTime;
							if(lavaRiseTime>1 && lavaRiseTime<2000) {
								WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
								wb.setCenter(player.getLocation());
								wb.setSize(bordersize);
								for(Player p : Bukkit.getOnlinePlayers()) {
									Main.players.add(p.getUniqueId());
								}
								am.startLavaRises(player);
								Main.task= Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
									@Override
									public void run() {
										fillLava(y,wb,player);
										y++;
										Bukkit.broadcastMessage("Lava is rising to y level - "+y);
									}
								},lavaRiseTime , lavaRiseTime );

							}else {
								player.sendMessage("Invalid time");
							}
						}else {
							player.sendMessage("Invalid Border size");
						}
					}
				}
			}


		}
		return false;

	}
	public void fillLava(int y, WorldBorder wb,Player player) {

		for(double x = ((Main.bordersize/2 * -1)+wb.getCenter().getX());x<((Main.bordersize / 2)+wb.getCenter().getX());x++) {
			for(double z = ((Main.bordersize/2 * -1)+wb.getCenter().getZ());z<((Main.bordersize / 2)+wb.getCenter().getZ());z++) {
				Location blocksLocation = new Location(player.getWorld(),x,y,z);
				if(blocksLocation.getBlock().getType() != Material.AIR ) continue;
				blocksLocation.getBlock().setType(Material.LAVA);
			}
		}
	}

}
