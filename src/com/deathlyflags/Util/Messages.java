package com.deathlyflags.Util;

public class Messages {

	private MessageManager mm;
	
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
		
	
	public Messages(){
		
		this.mm = new MessageManager();
		
		this.prefix = mm.getMessage("settings.prefix").replace("&", "§");
		this.joinmessage = prefix + mm.getMessage("settings.joinmessage");
		this.quitmessage = prefix + mm.getMessage("settings.quitmessage");
		this.noperm = prefix + mm.getMessage("settings.noperm");
		this.deathmessage = prefix + mm.getMessage("settings.deathmessage");
		this.chatmessage = prefix + mm.getMessage("settings.chatmessage");
		this.whoisonline = prefix + mm.getMessage("settings.whoisonline");
		this.ffamessage = prefix + mm.getMessage("settings.ffamessage");
		this.setspawn = prefix + mm.getMessage("settings.setspawn");
		this.quitlocation = prefix + mm.getMessage("settings.quitlocation");
	}
	
	public MessageManager getMsgManager(){
		return this.mm;
	}
	
}
