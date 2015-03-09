package com.deathlyflags.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.deathlyflags.FFA.FFAPlugin;
import com.deathlyflags.Util.Inventory;

public class RespawnManager implements Listener {

	@EventHandler
	public void RespawnManaging(PlayerRespawnEvent e) {

		final Player p = e.getPlayer();

		if (FFAPlugin.ingame.contains(p.getName())) {
			e.setRespawnLocation(FFAPlugin.getInstance().getSpawnLocation());
			new Inventory().GiveInventory(p);
		}

	}
}
