package com.deathlyflags.Util;

public class Settings {

	private SettingsManager mm;
	
	public final String prefix;
	public final String joinmessage;
	public final String quitmessage;
	public final String noperm;
	public final String deathmessage;
	public final String chatmessage;
	public final String whoisonline;
	public final String ffamessage;
	public final String setspawn;
	public final String quitlocation;
	
	public final Boolean autoJoin;
	public final Boolean fallDamage;
	public final int fasUses;
		
	
	public Settings(){
		
		this.mm = new SettingsManager();
		
		this.prefix = mm.getMessage("messages.prefix").replace("&", "§");
		this.joinmessage = prefix + mm.getMessage("messages.joinMessage");
		this.quitmessage = prefix + mm.getMessage("messages.quitMessage");
		this.noperm = prefix + mm.getMessage("messages.noPerm");
		this.deathmessage = prefix + mm.getMessage("messages.deathMessage");
		this.chatmessage = mm.getMessage("messages.chatMessage");
		this.whoisonline = prefix + mm.getMessage("messages.whosOnline");
		this.ffamessage = prefix + mm.getMessage("messages.ffaMessage");
		this.setspawn = prefix + mm.getMessage("messages.setSpawn");
		this.quitlocation = prefix + mm.getMessage("messages.quitLocation");
		
		this.autoJoin = new Boolean(mm.getMessage("settings.autoJoin"));
		this.fallDamage = new Boolean(mm.getMessage("settings.fallDamage"));
		this.fasUses = new Integer(mm.getMessage("settings.fasUses"));
	}
	
	public SettingsManager getMsgManager(){
		return this.mm;
	}
	
}
