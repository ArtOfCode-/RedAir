package uk.co.riversparrow.redair;

import uk.co.riversparrow.redair.io.ConfigFile;

import java.io.File;
import java.io.IOException;

import org.bukkit.World;
import org.bukkit.block.Block;

public class Utils {
	public RedAir plugin;
	
	public Utils(RedAir reference) {
		plugin = reference;
	}
	
	public boolean pluginDataReady() {
		String configFilePath = combinePaths(getDataPath(), "config.yml");
		ConfigFile configFile = new ConfigFile(configFilePath);
		if(configFile.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void createPluginData() throws IOException {
		File dataFolder = plugin.getDataFolder();
		dataFolder.mkdirs();
		String configFilePath = combinePaths(getDataPath(), "config.yml");
		ConfigFile configFile = new ConfigFile(configFilePath);
		configFile.create();
	}
	
	public String getDataPath() {
		String fileSeparator = File.separator;
		File dataFolder = plugin.getDataFolder();
		String dataPath = dataFolder.getAbsolutePath();
		if(dataPath.endsWith(fileSeparator)) {
			return dataPath;
		} else {
			return dataPath + fileSeparator;
		}
	}
	
	public String combinePaths(String path1, String path2) {
		String fileSeparator = File.separator;
		String path = "";
		if(path2.startsWith(fileSeparator) && !path1.endsWith(fileSeparator)) {
			path = path1 + path2;
		} else {
			path = path1 + fileSeparator + path2;
		}
		return path;
	}
	
	public Block getBlockAtCoords(Coordinates coords, World world) {
		return world.getBlockAt(coords.x, coords.y, coords.z);
	}
	
	public World getWorld(String worldName) {
		return plugin.getServer().getWorld(worldName);
	}
}
