package com.deathlyflags.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.deathlyflags.FFA.FFAPlugin;

public class PlayerChat implements Listener {

	@EventHandler
	public void MethodName(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		if (FFAPlugin.ingame.contains(p.getName())) {

			e.setFormat(FFAPlugin.getInstance().getMessages().chatmessage
					.replace("&", "§"));

		}

	}

}
