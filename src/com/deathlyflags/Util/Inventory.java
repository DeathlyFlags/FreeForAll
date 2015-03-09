package com.deathlyflags.Util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.deathlyflags.FFA.FFAPlugin;

public class Inventory {
	private FFAPlugin plugin;
	
	public Inventory() {
		this.plugin = FFAPlugin.getInstance();
	}
	
	public void GiveInventory(final Player p) {
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
