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
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

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

				Location loc = e.getClickedBlock().getLocation();

				if (getWorldGuard() != null) {
					RegionContainer container = getWorldGuard().getRegionContainer();
					RegionManager regions = container.get(loc.getWorld());
					ProtectedRegion region = regions.getRegion("spawn");

					if (region.getFlag(DefaultFlag.LIGHTER).equals("allow")) {
						IgniteBlock(loc);
					}
					is.setDurability((short) (is.getDurability() + fasUses));
					if(is.getDurability() >= 65)
						p.setItemInHand(null);
				} else {
					IgniteBlock(loc);
					is.setDurability((short) (is.getDurability() + fasUses));
					if(is.getDurability() >= 65)
						p.setItemInHand(null);
				}

			}
		}
	}
	
	private void IgniteBlock(Location loc){
		loc.add(0.5, 1, 0.5);
		if (loc.getBlock().getType() == Material.AIR) {
			loc.getWorld().playSound(loc, Sound.FIRE_IGNITE,
					1.0F,
					new Random().nextFloat() * 0.4F + 0.8F);
			loc.getBlock().setType(Material.FIRE);
			
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
