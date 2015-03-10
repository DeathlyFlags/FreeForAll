package com.deathlyflags.Util;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.deathlyflags.FFA.FFAPlugin;



public class SettingsManager {

	private final File file;
	private final FileConfiguration config;
	
	public SettingsManager(){
		
		this.file = new File("plugins/" + FFAPlugin.getInstance().getName() + "/", "settings.yml");
		this.config = YamlConfiguration.loadConfiguration(this.file);
		
		this.checkIfExists();
		
	}
	
	public File getFile(){
		return this.file;
	}
	
	public FileConfiguration getConfig(){
		return this.config;
	}
	
	public String getMessage(String path){
		return this.config.getString(path);
	}
	
	public void checkIfExists(){
		
		if(this.file.exists() == false){
			
			this.addDefaultStrings();
			
		}
		
	}
	
	private void addDefaultStrings(){
		
		this.config.addDefault("messages.prefix", "&7[&3FFA&7]: &r");
		this.config.addDefault("messages.joinMessage", "&6xplayerx &3has joined FFA!");
		this.config.addDefault("messages.quitMessage", "&6xplayerx &3has left FFA!");
		this.config.addDefault("messages.noPerm", "&cYou're not permitted to do this!");
		this.config.addDefault("messages.deathMessage", "&3" + "The player" + "&6" + " xplayer1x " + "&3" + "has been killed by" + "&6" + " xplayer2x " + "&3.");
		this.config.addDefault("messages.chatMessage", "&a" + "%s" + "&7" + ": " + "%s");
		this.config.addDefault("messages.whosOnline", "&3These players are online: ");
		this.config.addDefault("messages.ffaMessage", "&3Use /ffa <join/quit/players/setspawn/setquitlocation> to do something!");
		this.config.addDefault("messages.setSpawn", "&cYou've set the FFA spawn!");
		this.config.addDefault("messages.quitLocation", "&cYou've set the FFA Quit Location!");
		
		this.config.addDefault("settings.autoJoin", false);
		this.config.addDefault("settings.fallDamage", false);
		this.config.addDefault("settings.fasUses", 3);
		
		this.config.addDefault("armourInventory", new String[] { "IRON_HELMET", "IRON_CHESTPLATE", "IRON_LEGGINGS", "IRON_BOOTS" });
		this.config.addDefault("itemInventory", new String[] { "IRON_SWORD#DAMAGE_ALL,1", "BOW", "FLINT_AND_STEEL", "FISHING_ROD", "ARROW:10" });
		
		this.config.addDefault("materialWhitelist", new String[] { "FIRE", "FLINT_AND_STEEL" });
		
		getConfig().options().header(
				"#############################################" + "\n" +
				"#      - FreeForAll for MinETSII.es -       #" + "\n" +
				"#############################################" + "\n" +
				"# Boolean options can be:" + "\n" +
				"#    > true = Option enabled" + "\n" +
				"#    > false = Option disabled" + "\n" +
				"# fasUses is the numer of times the player can use the Flint and Steel" + "\n" +
				"# Don't change the order in armourInventory" + "\n" +
				"# To add enchantments follow: IRON_SWORD#DAMAGE_ALL,1" + "\n" +
				"#    > being ITEM_MATERIAL#ENCHANTMENT,LEVEL" + "\n" +
				"# Permissions" + "\n" +
				"#    > ffa.itemdrop - Players with this permission can drop items" + "\n" +
				"#    > ffa.itempickup - Players with this permission can pick up items" + "\n" +
				"#    > ffa.build - Players with this permission can build (destroy and place blocks)" + "\n" +
				"#    > ffa.setspawn - Let the player use the command /lc reload" + "\n" +
				"#    > ffa.setquitlocation - Let the player use the command /lc reload" + "\n" +
				"#############################################" + "\n"
		);
		
		this.config.options().copyDefaults(true);
		
		this.save();
	}
	
	public void save(){
		
		try {
			this.config.save(this.file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void load(){
		try {
			this.config.load(this.file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
