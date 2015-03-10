package com.deathlyflags.Util;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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

						for (String armour : FFAPlugin.getInstance()
								.getSettings().armourInventory) {
							Material block = (StringUtils.isNumeric(armour)) ? Material
									.getMaterial(new Integer(armour))
									: Material.getMaterial(armour);
							switch (FFAPlugin.getInstance().getSettings().armourInventory
									.indexOf(armour)) {
							case 0:
								p.getInventory()
										.setHelmet(new ItemStack(block));
								break;
							case 1:
								p.getInventory().setChestplate(
										new ItemStack(block));
								break;
							case 2:
								p.getInventory().setLeggings(
										new ItemStack(block));
								break;
							case 3:
								p.getInventory().setBoots(new ItemStack(block));
								break;
							}
						}

						for (String inventory : FFAPlugin.getInstance()
								.getSettings().itemInventory) {
							String[] item = inventory.split(":");
							String[] itemMaterial = item[0].split("#");

							String materialName = itemMaterial[0];
							Integer amount = (item.length == 1) ? 1
									: new Integer(item[1]);
							Material block = (StringUtils
									.isNumeric(materialName)) ? Material
									.getMaterial(new Integer(materialName))
									: Material.getMaterial(materialName);

							ItemStack is = new ItemStack(block, amount);
							if (itemMaterial.length > 1) {
								Boolean first = true;
								try {
									for (String enchantments : itemMaterial) {
										if (!first) {
											String[] enchantment = enchantments
													.split(",");
											Integer level = (enchantment.length == 1) ? 1
													: new Integer(
															enchantment[1]);
											is.addEnchantment(Enchantment
													.getByName(enchantment[0]),
													level);
										} else
											first = false;
									}
								} catch (Exception e) {
								}
							}
							p.getInventory().addItem(is);
						}
					}
				}, 1L);
	}
}
