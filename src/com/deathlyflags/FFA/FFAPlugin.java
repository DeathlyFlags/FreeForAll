package com.deathlyflags.FFA;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.deathlyflags.Commands.FFACommands;
import com.deathlyflags.Commands.SettingFileContent;
import com.deathlyflags.Listener.BlockManager;
import com.deathlyflags.Listener.DeathManager;
import com.deathlyflags.Listener.FallDamage;
import com.deathlyflags.Listener.FlintAndSteel;
import com.deathlyflags.Listener.HungerManager;
import com.deathlyflags.Listener.ItemManager;
import com.deathlyflags.Listener.MobDamage;
import com.deathlyflags.Listener.PlayerChat;
import com.deathlyflags.Listener.QuitEvent;
import com.deathlyflags.Listener.RespawnManager;
import com.deathlyflags.Listener.WeatherChange;
import com.deathlyflags.Util.Messages;

public class FFAPlugin extends JavaPlugin {

	public static ArrayList<String> ingame;
	private static FFAPlugin instance;

	public static FFAPlugin getInstance() {
		return instance;
	}

	private Messages messages;
	private SettingFileContent spawn;
	private SettingFileContent quit;

	public Messages getMessages() {
		return this.messages;
	}

	@Override
	public void onLoad() {

		instance = this;
		ingame = new ArrayList<String>();

	}

	@Override
	public void onEnable() {

		messages = new Messages();
		spawn = new SettingFileContent(new File("plugins/" + this.getName()
				+ "/", "Spawn.yml"));
		quit = new SettingFileContent(new File("plugins/" + this.getName()
				+ "/", "Quit.yml"));

		this.repair();
		this.repairconfig();
		this.registerListener(Bukkit.getPluginManager());
		this.getCommand("ffa").setExecutor(new FFACommands());

	}

	@Override
	public void onDisable() {

		for (Player player : Bukkit.getOnlinePlayers()) {

			if (ingame.contains(player.getName())) {

				player.getInventory().clear();
				player.getInventory().setArmorContents(null);
				player.setLevel(0);
				player.teleport(this.getQuitLocation());

			}

		}

	}

	public void registerListener(PluginManager pm) {

		pm.registerEvents(new BlockManager(this), this);
		pm.registerEvents(new DeathManager(), this);
		pm.registerEvents(new FallDamage(), this);
		pm.registerEvents(new FlintAndSteel(), this);
		pm.registerEvents(new HungerManager(), this);
		pm.registerEvents(new ItemManager(), this);
		pm.registerEvents(new MobDamage(), this);
		pm.registerEvents(new PlayerChat(), this);
		pm.registerEvents(new QuitEvent(), this);
		pm.registerEvents(new RespawnManager(this), this);
		pm.registerEvents(new WeatherChange(), this);

	}

	public static void sendGlobalMessage(String... strings) {

		for (Player player : Bukkit.getOnlinePlayers()) {

			if (ingame.contains(player.getName())) {
				for (String s : strings) {
					player.sendMessage(s);
				}
			}

		}

	}

	/***
	 * 
	 * Spawn Config
	 * 
	 */

	private void repair() {

		FileConfiguration config = this.spawn.getConfig();
		Location location = Bukkit.getWorlds().get(0).getSpawnLocation();

		config.addDefault("Spawn.World", location.getWorld().getName());

		config.addDefault("Spawn.PosX", location.getX());
		config.addDefault("Spawn.PosY", location.getY());
		config.addDefault("Spawn.PosZ", location.getZ());

		config.addDefault("Spawn.PosYaw", location.getPitch());
		config.addDefault("Spawn.PosPitch", location.getYaw());

		config.options().copyHeader(true);
		config.options().copyDefaults(true);

		this.spawn.saveConfig();

	}

	public Location getSpawnLocation() {

		FileConfiguration config = this.spawn.getConfig();

		try {
			return new Location(
					Bukkit.getWorld(config.getString("Spawn.World")),
					config.getDouble("Spawn.PosX"),
					config.getDouble("Spawn.PosY"),
					config.getDouble("Spawn.PosZ"),
					(float) config.getDouble("Spawn.PosYaw"),
					(float) config.getDouble("Spawn.PosPitch"));
		} catch (Exception ex) {
			return Bukkit.getWorlds().get(0).getSpawnLocation();
		}

	}

	public void setSpawnLocation(Location loc) {

		FileConfiguration config = this.spawn.getConfig();

		config.set("Spawn.World", loc.getWorld().getName());

		config.set("Spawn.PosX", loc.getX());
		config.set("Spawn.PosY", loc.getY());
		config.set("Spawn.PosZ", loc.getZ());

		config.set("Spawn.PosYaw", loc.getPitch());
		config.set("Spawn.PosPitch", loc.getYaw());

		this.spawn.saveConfig();

	}

	/***
	 * 
	 * Quit Config
	 * 
	 */
	private void repairconfig() {

		FileConfiguration config = this.quit.getConfig();
		Location location = Bukkit.getWorlds().get(0).getSpawnLocation();

		config.addDefault("Quit.World", location.getWorld().getName());

		config.addDefault("Quit.PosX", location.getX());
		config.addDefault("Quit.PosY", location.getY());
		config.addDefault("Quit.PosZ", location.getZ());

		config.addDefault("Quit.PosYaw", location.getPitch());
		config.addDefault("Quit.PosPitch", location.getYaw());

		config.options().copyHeader(true);
		config.options().copyDefaults(true);

		this.quit.saveConfig();

	}

	public Location getQuitLocation() {

		FileConfiguration config = this.quit.getConfig();

		try {
			return new Location(
					Bukkit.getWorld(config.getString("Quit.World")),
					config.getDouble("Quit.PosX"),
					config.getDouble("Quit.PosY"),
					config.getDouble("Quit.PosZ"),
					(float) config.getDouble("Quit.PosYaw"),
					(float) config.getDouble("Quit.PosPitch"));
		} catch (Exception ex) {
			return Bukkit.getWorlds().get(0).getSpawnLocation();
		}

	}

	public void setQuitLocation(Location loc) {

		FileConfiguration config = this.quit.getConfig();

		config.set("Quit.World", loc.getWorld().getName());

		config.set("Quit.PosX", loc.getX());
		config.set("Quit.PosY", loc.getY());
		config.set("Quit.PosZ", loc.getZ());

		config.set("Quit.PosYaw", loc.getPitch());
		config.set("Quit.PosPitch", loc.getYaw());

		this.quit.saveConfig();

	}
}
