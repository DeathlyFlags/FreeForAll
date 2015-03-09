package com.deathlyflags.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.deathlyflags.FFA.FFAPlugin;

public class ItemManager implements Listener {

	@EventHandler
	public void MethodName(PlayerDropItemEvent e) {
		Player p = e.getPlayer();

		if (FFAPlugin.ingame.contains(p.getName())) {
			if (!p.hasPermission("ffa.itemdrop"))
				e.setCancelled(true);
		}
	}

	@EventHandler
	public void PickupItem(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();

		if (FFAPlugin.ingame.contains(p.getName())) {
			if (!p.hasPermission("ffa.itempickup"))
				e.setCancelled(true);
		}
	}

}
