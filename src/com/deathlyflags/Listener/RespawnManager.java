package com.deathlyflags.Listener;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import com.deathlyflags.FFA.FFAPlugin;

public class RespawnManager implements Listener {

	private FFAPlugin plugin;

	public RespawnManager(FFAPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void RespawnManaging(PlayerRespawnEvent e) {

		final Player p = e.getPlayer();

		if (FFAPlugin.ingame.contains(p.getName())) {
			e.setRespawnLocation(FFAPlugin.getInstance().getSpawnLocation());

			Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin,
					new Runnable() {
						public void run() {
							p.getInventory().clear();
							p.getInventory().setArmorContents(null);
							p.setHealth(20D);
							p.setFoodLevel(20);

							p.getInventory().setHelmet(
									new ItemStack(Material.IRON_HELMET));
							p.getInventory().setChestplate(
									new ItemStack(Material.IRON_CHESTPLATE));
							p.getInventory().setLeggings(
									new ItemStack(Material.IRON_LEGGINGS));
							p.getInventory().setBoots(
									new ItemStack(Material.IRON_BOOTS));
							p.getInventory().addItem(
									new ItemStack(Material.IRON_SWORD));
							p.getInventory().addItem(
									new ItemStack(Material.BOW));
							p.getInventory().addItem(
									new ItemStack(Material.FLINT_AND_STEEL));
							p.getInventory().addItem(
									new ItemStack(Material.FISHING_ROD));
							p.getInventory().addItem(
									new ItemStack(Material.ARROW, 10));
						}
					}, 1L);

		}

	}
}
