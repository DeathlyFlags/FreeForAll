package com.deathlyflags.Commands;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.deathlyflags.FFA.FFAPlugin;
import com.deathlyflags.Util.Inventory;

public class FFACommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label,
			String[] args) {

		if (cs instanceof Player) {

			Player p = (Player) cs;

			if (args.length != 1) {

				p.sendMessage(FFAPlugin.getInstance().getSettings().ffamessage
						.replace("&", "§"));

			} else  {
				switch (args[0]) {

				case "join":
					if (!FFAPlugin.ingame.contains(p.getName())) {
						FFAPlugin.ingame.add(p.getName());
						
		                FFAPlugin.getInstance().getPlayerInvConf().set(p.getName() + ".inventory", p.getInventory().getContents());
		                FFAPlugin.getInstance().getPlayerInvConf().set(p.getName() + ".armor", p.getInventory().getArmorContents());

						FFAPlugin.sendGlobalMessage(FFAPlugin.getInstance()
								.getSettings().joinmessage.replaceAll(
								"xplayerx", p.getName()).replace("&", "§"));

						new Inventory().GiveInventory(p);

						p.teleport(FFAPlugin.getInstance().getSpawnLocation());
					}
					break;

				case "quit":
				case "leave":
					if (FFAPlugin.ingame.contains(p.getName())) {

						FFAPlugin.sendGlobalMessage(FFAPlugin.getInstance()
								.getSettings().quitmessage.replaceAll(
								"xplayerx", p.getName()).replace("&", "§"));

						p.getInventory().clear();
						p.getInventory().setArmorContents(null);
						p.setLevel(0);
						p.setHealth(20D);
						p.setFoodLevel(20);

						p.teleport(FFAPlugin.getInstance().getQuitLocation());

						p.setGameMode(GameMode.SURVIVAL);

		                Object a = FFAPlugin.getInstance().getPlayerInvConf().get(p.getName() + ".inventory");
		                Object b = FFAPlugin.getInstance().getPlayerInvConf().get(p.getName() + ".armor");
		                if(a == null || b == null){
		                    p.sendMessage("No saved inventory to load");
		                    return true;
		                }
		                ItemStack[] inventory = null;
		                ItemStack[] armor = null;
		                if (a instanceof ItemStack[]){
		                      inventory = (ItemStack[]) a;
		                } else if (a instanceof List){
		                        @SuppressWarnings("unchecked")
								List<ItemStack> lista = (List<ItemStack>) a;
		                        inventory = (ItemStack[]) lista.toArray(new ItemStack[0]);
		                }
		                if (b instanceof ItemStack[]){
		                        armor = (ItemStack[]) b;
		                  } else if (b instanceof List){
		                      List<ItemStack> listb = (List<ItemStack>) b;
		                      armor = (ItemStack[]) listb.toArray(new ItemStack[0]);
		                  }
		                p.getInventory().setContents(inventory);
		                p.getInventory().setArmorContents(armor);
						
						FFAPlugin.ingame.remove(p.getName());
					}
					break;

				case "setspawn":
					if (p.hasPermission("ffa.setspawn")) {

						FFAPlugin.getInstance().setSpawnLocation(
								p.getLocation());
						p.sendMessage(FFAPlugin.getInstance().getSettings().setspawn
								.replace("&", "§"));

					} else {

						p.sendMessage(FFAPlugin.getInstance().getSettings().noperm
								.replace("&", "§"));

					}
					break;

				case "players":
					for (String playername : FFAPlugin.ingame) {
						p.sendMessage(FFAPlugin.getInstance().getSettings().whoisonline
								.replace("&", "§") + playername);
					}
					break;

				case "setquitlocation":
					if (p.hasPermission("ffa.setquitlocation")) {
						FFAPlugin.getInstance()
								.setQuitLocation(p.getLocation());
						p.sendMessage(FFAPlugin.getInstance().getSettings().quitlocation
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
