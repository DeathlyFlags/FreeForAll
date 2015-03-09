package com.deathlyflags.Commands;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.deathlyflags.FFA.FFAPlugin;

public class FFACommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label,
			String[] args) {

		if (cs instanceof Player) {

			Player p = (Player) cs;

			if (args.length == 0) {

				p.sendMessage(FFAPlugin.getInstance().getMessages().ffamessage
						.replace("&", "§"));

			} else if (args.length == 1) {

				if (args[0].equalsIgnoreCase("join")) {

					if (!FFAPlugin.ingame.contains(p.getName())) {
						FFAPlugin.ingame.add(p.getName());

						FFAPlugin.sendGlobalMessage(FFAPlugin.getInstance()
								.getMessages().joinmessage.replaceAll(
								"xplayerx", p.getName()).replace("&", "§"));

						p.getInventory().clear();
						p.getInventory().setArmorContents(null);
						p.setHealth(20D);
						p.setFoodLevel(20);
						p.setGameMode(GameMode.ADVENTURE);

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
						p.getInventory().addItem(new ItemStack(Material.BOW));
						p.getInventory().addItem(
								new ItemStack(Material.FLINT_AND_STEEL));
						p.getInventory().addItem(
								new ItemStack(Material.FISHING_ROD));
						p.getInventory().addItem(
								new ItemStack(Material.ARROW, 10));

						p.teleport(FFAPlugin.getInstance().getSpawnLocation());

					}

				} else if (args[0].equalsIgnoreCase("quit")
						|| args[0].equalsIgnoreCase("leave")) {

					if (FFAPlugin.ingame.contains(p.getName())) {

						FFAPlugin.sendGlobalMessage(FFAPlugin.getInstance()
								.getMessages().quitmessage.replaceAll(
								"xplayerx", p.getName()).replace("&", "§"));

						p.getInventory().clear();
						p.getInventory().setArmorContents(null);
						p.setLevel(0);
						p.setHealth(20D);
						p.setFoodLevel(20);

						p.teleport(FFAPlugin.getInstance().getQuitLocation());

						p.setGameMode(GameMode.SURVIVAL);

						FFAPlugin.ingame.remove(p.getName());

					}

				} else if (args[0].equalsIgnoreCase("setspawn")) {

					if (p.hasPermission("ffa.setspawn")) {

						FFAPlugin.getInstance().setSpawnLocation(
								p.getLocation());
						p.sendMessage(FFAPlugin.getInstance().getMessages().setspawn
								.replace("&", "§"));

					} else {

						p.sendMessage(FFAPlugin.getInstance().getMessages().noperm
								.replace("&", "§"));

					}

				} else if (args[0].equalsIgnoreCase("players")) {

					for (String playername : FFAPlugin.ingame) {
						p.sendMessage(FFAPlugin.getInstance().getMessages().whoisonline
								.replace("&", "§") + playername);
					}

				} else if (args[0].equalsIgnoreCase("setquitlocation")) {
					if (p.hasPermission("ffa.setquitlocation")) {
						FFAPlugin.getInstance()
								.setQuitLocation(p.getLocation());
						p.sendMessage(FFAPlugin.getInstance().getMessages().quitlocation
								.replace("&", "§"));
					}
				}

			}

			return true;
		} else {

			return false;
		}
	}

}
