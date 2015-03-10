package com.deathlyflags.Listener;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.deathlyflags.FFA.FFAPlugin;

public class FallDamage implements Listener {

	@EventHandler
	public void FallDamageManager(EntityDamageEvent e) {
		if (e.getEntity().getType() == EntityType.PLAYER && !FFAPlugin.getInstance().getSettings().fallDamage) {
			Player p = (Player) e.getEntity();
			
			if (FFAPlugin.ingame.contains(p.getName()) && e.getCause() == DamageCause.FALL) {
					e.setCancelled(true);
			}
		}
	}
}
