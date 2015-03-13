package uk.co.riversparrow.redair;

import java.io.IOException;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

import uk.co.riversparrow.redair.command.Executor;
import uk.co.riversparrow.redair.io.MapCache;

public final class RedAir extends JavaPlugin {
	private Utils utils;
	private final String beta = Character.toString((char) 0x3B2);
	private final String buildNumber = "036" + beta;
	private final String formattedName = ChatColor.DARK_RED + "Red" + ChatColor.AQUA + "Air" + ChatColor.RESET;

	@Override
	public void onEnable() {
		utils = new Utils(this);
		try {
			Cache.setCacheFile(new MapCache(utils.getDataPath() + "cache.ch", utils));
			Cache.loadFromFile();
		} catch(Exception ex) {
			this.log("Could not enable: could not read cache file: " + ex.getMessage());
			this.log("Exception thrown has " + ex.getClass());
			this.disablePlugin(ex.getMessage());
		}
		if(!utils.pluginDataReady()) {
			try {
				utils.createPluginData();
			} catch(IOException ex) {
				this.log("Could not enable: failed to create files: " + ex.getMessage());
				this.disablePlugin(ex.getMessage());
				return;
			}
		}
		new BlockRedstoneListener(this);
		this.getCommand("redair").setExecutor(new Executor());
		this.log(formattedName + " build " + buildNumber + " is ready.");
		this.getServer().broadcastMessage(formattedName + " build " + buildNumber + " is ready.");
	}

	@Override
	public void onDisable() {	
		HashMap<Block, Block> cachedMaps = Cache.getMaps();
		try {
			Cache.getCacheFile().saveFromHashMap(cachedMaps);
		} catch (Exception e) {
			this.log("Could not save your wireless mapping data. All data will be lost.");
		}
	}

	public void disablePlugin(String reason) {
		this.getLogger().info("RedAir has disabled itself, reason: " + reason);
		this.setEnabled(false);
	}
	
	public void log(String log) {
		this.getLogger().info(log);
	}
}
