package com.deathlyflags.Listener;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.deathlyflags.FFA.FFAPlugin;

public class DeathManager implements Listener {

	@EventHandler
	public void DeathManaging(PlayerDeathEvent e) {

		Player p = e.getEntity();

		if (FFAPlugin.ingame.contains(p.getName())) {

			e.getDrops().clear();

		}

	}

	@EventHandler
	public void DeathMessages(PlayerDeathEvent e) {

		Player p = e.getEntity();

		if (FFAPlugin.ingame.contains(p.getName())) {

			e.setDeathMessage(null);

			if (p.getKiller() != null) {

				Player killer = p.getKiller();
				Damageable killerD = killer;

				FFAPlugin
						.sendGlobalMessage(FFAPlugin.getInstance()
								.getSettings().deathmessage
								.replaceAll("xplayer1x", killer.getName())
								.replace("&", "§")
								.replaceAll("xplayer2x", p.getName()));

				killerD.setHealth(killerD.getMaxHealth());

			}

		}

	}

}
