package com.deathlyflags.Listener;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MobDamage implements Listener {

	@EventHandler
	public void MDmg(EntityDamageByEntityEvent e) {

		if (e.getEntity().getType() == EntityType.PLAYER
				&& e.getDamager() instanceof Monster) {
			e.setCancelled(true);
		}

		if (e.getEntity().getType() == EntityType.PLAYER
				&& e.getDamager() instanceof Projectile) {
			Projectile proj = (Projectile) e.getDamager();

			if (!(proj.getShooter() instanceof Player)) {
				e.setCancelled(true);
			}
		}
	}
}
