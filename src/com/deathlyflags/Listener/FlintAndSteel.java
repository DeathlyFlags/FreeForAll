package com.deathlyflags.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;

public class FlintAndSteel implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void FlintandSteel(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		int fasUses = (int) Math.ceil(65 / FFAPlugin.getInstance().getMessages().fasUses);
		
		if (FFAPlugin.ingame.contains(p.getName())
				&& e.getAction() == Action.RIGHT_CLICK_BLOCK
				&& p.getItemInHand() != null) {

			ItemStack is = p.getItemInHand();

			if (is.getType() == Material.FLINT_AND_STEEL) {

				Location loc = e.getClickedBlock().getLocation();

				if (getWorldGuard() != null) {
					RegionContainer container = getWorldGuard().getRegionContainer();
					RegionManager regions = container.get(loc.getWorld());
					ApplicableRegionSet region = regions.getApplicableRegions(loc);

					if (region.getFlag(DefaultFlag.LIGHTER) == StateFlag.State.ALLOW) {
						is.setDurability((short) (is.getDurability() + fasUses));
						if(is.getDurability() >= 65)
							p.setItemInHand(null);
					} else {
						e.setCancelled(true);
					}
				} else {
					is.setDurability((short) (is.getDurability() + fasUses));
					if(is.getDurability() >= 65)
						p.setItemInHand(null);
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
