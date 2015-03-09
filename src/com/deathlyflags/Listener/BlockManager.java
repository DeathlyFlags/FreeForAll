package com.deathlyflags.Listener;

import java.util.ArrayList;

import org.bukkit.Material;
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
			if (!p.hasPermission("ffa.blockbreak")) {
				e.setCancelled(true);
			}
		}
	}

	private FFAPlugin pl;

	private ArrayList<Material> place_whitelist;

	public BlockManager(FFAPlugin pl) {
		this.pl = pl;
		place_whitelist = new ArrayList<Material>();
		place_whitelist.add(Material.FIRE);
	}

	@EventHandler
	public void BlockP(BlockPlaceEvent e) {
		if (FFAPlugin.ingame.contains(e.getPlayer().getName())) {
			if (!place_whitelist.contains(e.getBlock().getType()))
				e.setCancelled(true);
		}
	}

}
