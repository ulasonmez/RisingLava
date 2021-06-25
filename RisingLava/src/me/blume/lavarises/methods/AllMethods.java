package me.blume.lavarises.methods;

import org.bukkit.entity.Player;

public class AllMethods {

	SavingEverything se = new SavingEverything();
	public void startLavaRises(Player p) {
		se.keepInventories();
		se.saveLevels();
		se.saveHealth();
		se.saveLocations(p);
		se.saveBlocks(p);
		
	}
	public void stopLavaRises(Player p) {
		se.giveInventories();
		se.giveLevels();
		se.giveHealth();
		se.giveLocations();
		se.giveBlocks(p);
	}
}
