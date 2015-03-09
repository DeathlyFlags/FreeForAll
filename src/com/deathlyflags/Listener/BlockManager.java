package com.deathlyflags.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.deathlyflags.FFA.FFAPlugin;

public class BlockManager implements Listener {

	@EventHandler
	public void BlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();

		if (FFAPlugin.ingame.contains(p.getName())) {
			if (!p.hasPermission("ffa.build")) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void BlockP(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (FFAPlugin.ingame.contains(e.getPlayer().getName())) {
			if (!p.hasPermission("ffa.build")) {
				e.setCancelled(true);
			}
		}
	}

}
