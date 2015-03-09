package com.deathlyflags.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.deathlyflags.FFA.FFAPlugin;

public class HungerManager implements Listener {

	@EventHandler
	public void FoodLevel(FoodLevelChangeEvent e) {

		Player p = (Player) e.getEntity();

		if (FFAPlugin.ingame.contains(p.getName())) {
			e.setFoodLevel(20);
		}

	}

}
