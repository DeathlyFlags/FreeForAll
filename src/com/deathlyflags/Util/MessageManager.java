package com.deathlyflags.Util;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.deathlyflags.FFA.FFAPlugin;



public class MessageManager {

	private final File file;
	private final FileConfiguration config;
	
	public MessageManager(){
		
		this.file = new File("plugins/" + FFAPlugin.getInstance().getName() + "/", "messages.yml");
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
		
		this.config.addDefault("settings.prefix", "&7[&3FFA&7]: &r");
		this.config.addDefault("settings.joinmessage", "&6xplayerx &3has joined FFA!");
		this.config.addDefault("settings.quitmessage", "&6xplayerx &3has left FFA!");
		this.config.addDefault("settings.noperm", "&cYou're not permitted to do this!");
		this.config.addDefault("settings.deathmessage", "&3" + "The player" + "&6" + " xplayer1x " + "&3" + "has been killed by" + "&6" + " xplayer2x " + "&3.");
		this.config.addDefault("settings.chatmessage", "&a" + "%s" + "&7" + ": " + "%s");
		this.config.addDefault("settings.whoisonline", "&3These players are online: ");
		this.config.addDefault("settings.ffamessage", "&3Use /ffa <join/quit/players/setspawn/setquitlocation> to do something!");
		this.config.addDefault("settings.setspawn", "&cYou've set the FFA spawn!");
		this.config.addDefault("settings.quitlocation", "&cYou've set the FFA Quit Location!");
		
		this.config.options().header("Here you can configure the message!");
		
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
