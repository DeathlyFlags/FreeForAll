package com.deathlyflags.Commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.deathlyflags.FFA.FFAPlugin;
import com.deathlyflags.Util.Inventory;

public class FFACommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label,
			String[] args) {

		if (cs instanceof Player) {

			Player p = (Player) cs;

			if (args.length != 1) {

				p.sendMessage(FFAPlugin.getInstance().getMessages().ffamessage
						.replace("&", "§"));

			} else  {
				switch (args[0]) {

				case "join":
					if (!FFAPlugin.ingame.contains(p.getName())) {
						FFAPlugin.ingame.add(p.getName());

						FFAPlugin.sendGlobalMessage(FFAPlugin.getInstance()
								.getMessages().joinmessage.replaceAll(
								"xplayerx", p.getName()).replace("&", "§"));

						new Inventory().GiveInventory(p);

						p.teleport(FFAPlugin.getInstance().getSpawnLocation());
					}
					break;

				case "quit":
				case "leave":
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
					break;

				case "setspawn":
					if (p.hasPermission("ffa.setspawn")) {

						FFAPlugin.getInstance().setSpawnLocation(
								p.getLocation());
						p.sendMessage(FFAPlugin.getInstance().getMessages().setspawn
								.replace("&", "§"));

					} else {

						p.sendMessage(FFAPlugin.getInstance().getMessages().noperm
								.replace("&", "§"));

					}
					break;

				case "players":
					for (String playername : FFAPlugin.ingame) {
						p.sendMessage(FFAPlugin.getInstance().getMessages().whoisonline
								.replace("&", "§") + playername);
					}
					break;

				case "setquitlocation":
					if (p.hasPermission("ffa.setquitlocation")) {
						FFAPlugin.getInstance()
								.setQuitLocation(p.getLocation());
						p.sendMessage(FFAPlugin.getInstance().getMessages().quitlocation
								.replace("&", "§"));
					}
					break;
				default:
					break;
				}

			}
			return true;
		} else {
			return false;
		}
	}

}
