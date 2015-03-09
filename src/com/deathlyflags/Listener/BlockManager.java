package com.deathlyflags.Listener;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.deathlyflags.FFA.FFAPlugin;
import com.deathlyflags.Util.Settings;

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
	

	private ArrayList<Material> place_whitelist;

	@SuppressWarnings("deprecation")
	public BlockManager() {
		place_whitelist = new ArrayList<Material>();
		for(String material : FFAPlugin.getInstance().getMessages().materialWhitelist){
			Material block = (StringUtils.isNumeric(material)) ? Material.getMaterial(new Integer(material)) : Material.getMaterial(material);
			place_whitelist.add(block);
		}
	}

	@EventHandler
	public void BlockP(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		ItemStack is = p.getItemInHand();
		
		if (FFAPlugin.ingame.contains(e.getPlayer().getName())) {
			if (!place_whitelist.contains(is.getType()) && !p.hasPermission("ffa.build")) {
				e.setCancelled(true);
			}
		}
	}

}
