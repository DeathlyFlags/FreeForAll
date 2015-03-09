package com.deathlyflags.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.deathlyflags.FFA.FFAPlugin;

public class QuitEvent implements Listener {

	@EventHandler
	public void QE(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		if (FFAPlugin.ingame.contains(p.getName())) {
			FFAPlugin.ingame.remove(p.getName());

			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			p.setLevel(0);

		}

	}

}
