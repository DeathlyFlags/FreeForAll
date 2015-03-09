package com.deathlyflags.Listener;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.deathlyflags.FFA.FFAPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class FlintAndSteel implements Listener {
	
	private final int fasUses = (int) Math.ceil(65 / FFAPlugin.getInstance().getMessages().fasUses);

	@EventHandler
	public void FlintandSteel(PlayerInteractEvent e) {

		Player p = e.getPlayer();

		if (FFAPlugin.ingame.contains(p.getName())
				&& e.getAction() == Action.RIGHT_CLICK_BLOCK
				&& p.getItemInHand() != null) {

			ItemStack is = p.getItemInHand();

			if (is.getType() == Material.FLINT_AND_STEEL) {
				is.setDurability((short) (is.getDurability() + fasUses));
				if(is.getDurability() >= 65)
					p.setItemInHand(null);

				Location loc = e.getClickedBlock().getLocation();

				if (getWorldGuard() != null) {
					WorldGuardPlugin worldGuard = getWorldGuard();
					
					if (worldGuard.canBuild(p, loc)) {
						loc.add(0.5, 1, 0.5);
						if (loc.getBlock().getType() == Material.AIR) {
							loc.getWorld().playSound(loc, Sound.FIRE_IGNITE,
									1.0F,
									new Random().nextFloat() * 0.4F + 0.8F);
							loc.getBlock().setType(Material.FIRE);
						}
					}
				} else {
					loc.add(0.5, 1, 0.5);
					if (loc.getBlock().getType() == Material.AIR) {
						loc.getWorld().playSound(loc, Sound.FIRE_IGNITE,
								1.0F,
								new Random().nextFloat() * 0.4F + 0.8F);
						loc.getBlock().setType(Material.FIRE);
					}
				}

			}
		}
	}

	private WorldGuardPlugin getWorldGuard() {
		Plugin plugin = Bukkit.getServer().getPluginManager()
				.getPlugin("WorldGuard");

		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}

		return (WorldGuardPlugin) plugin;
	}
}
