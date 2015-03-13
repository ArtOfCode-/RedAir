package uk.co.riversparrow.redair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import uk.co.riversparrow.redair.io.FileFormatException;
import uk.co.riversparrow.redair.io.MapCache;

public class Cache {
	private static MapCache cacheFile;
	
	static void setCacheFile(MapCache cache) throws IOException {
		cacheFile = cache;
		createCacheFile();
	}
	static MapCache getCacheFile() {
		return cacheFile;
	}
	
	static void createCacheFile() throws IOException {
		File parentDir = new File(cacheFile.getFile().getParent());
		if(!parentDir.exists()) {
			parentDir.mkdirs();
		}
		cacheFile.create();
	}

	private static HashMap<Block, Block> maps = new HashMap<Block, Block>();
	public static void setMaps(HashMap<Block, Block> newMaps) {
		maps = newMaps;
	}
	public static HashMap<Block, Block> getMaps() {
		return maps;
	}

	public static HashMap<Player, Block> firstBlocks = new HashMap<Player, Block>();

	public static void addMapping(Block firstBlock, Block secondBlock) {
		maps.put(firstBlock, secondBlock);
		/*try {
			cacheFile.addEntry(firstBlock, secondBlock);
			return true;
		} catch (IOException e) {
			plugin.getLogger().info(ChatColor.DARK_RED
							+ "[RedAir WARN] Could not save mapping to disk. Reloads or restarts will cause mapping to be lost.");
			return false;
		}*/
	}

	public static void removeMapping(Block key) {
		maps.remove(key);
		/*try {
			cacheFile.removeEntry(key);
			return true;
		} catch(Exception ex) {
			plugin.getLogger().info(ChatColor.DARK_RED
							+ "[RedAir WARN] Could not remove mapping from disk. Reloads or restarts will cause mapping to be re-activated.");
			return false;
		}*/
	}

	public static boolean isInMaps(Block check) {
		Set<Block> keys = maps.keySet();
		return keys.contains(check);
	}

	public static Block getMappedBlock(Block key) {
		return maps.get(key);
	}
	
	public static void loadFromFile() throws FileNotFoundException, FileFormatException, IOException {
		maps = cacheFile.read();
	}
}
