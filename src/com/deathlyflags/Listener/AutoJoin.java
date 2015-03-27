package com.deathlyflags.Listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerJoinEvent;

import com.deathlyflags.FFA.FFAPlugin;

public class AutoJoin implements Listener {

	@EventHandler
	public void AutoJoinManager(PlayerJoinEvent e) {
		if (FFAPlugin.getInstance().getSettings().autoJoin) {
			Player p = (Player) e.getPlayer();
			
			Bukkit.dispatchCommand(p, "ffa join");
		}
	}
}
