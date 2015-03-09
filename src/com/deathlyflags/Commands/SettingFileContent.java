package com.deathlyflags.Commands;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SettingFileContent {

	private File file;
	private FileConfiguration config;

	public SettingFileContent(File file) {
		this.file = file;
		this.config = YamlConfiguration.loadConfiguration(this.file);
	}

	public File getFile() {
		return this.file;
	}

	public FileConfiguration getConfig() {
		return this.config;
	}

	public void saveConfig() {
		try {
			this.config.save(this.file);
		} catch (Exception ex) {
		}
	}

	public void loadConfig() {
		try {
			this.config.load(this.file);
		} catch (Exception ex) {
		}
	}

}
