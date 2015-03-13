package uk.co.riversparrow.redair;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.Location;
import org.bukkit.Material;

public class BlockRedstoneListener implements Listener {
	private RedAir plugin;
	
	public BlockRedstoneListener(RedAir plg) {
		plugin = plg;
		if(plugin.isEnabled()) {
			plugin.getServer().getPluginManager().registerEvents(this, plugin);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockRedstoneChange(BlockRedstoneEvent evt) {
		//plugin.getLogger().info("Event called!");
		Block block = evt.getBlock();
		List<Block> surroundingBlocks = new ArrayList<Block>();
		surroundingBlocks.add(getBlockInDirection(block, "NORTH"));
		surroundingBlocks.add(getBlockInDirection(block, "SOUTH"));
		surroundingBlocks.add(getBlockInDirection(block, "EAST"));
		surroundingBlocks.add(getBlockInDirection(block, "WEST"));
		for(Block checkBlock : surroundingBlocks) {
			if(Cache.isInMaps(checkBlock)) {
				Block mappedBlock = Cache.getMappedBlock(checkBlock);
				if(!checkBlock.isBlockPowered()) {
					mappedBlock.setType(Material.REDSTONE_BLOCK);
				} else {
					mappedBlock.setType(Material.IRON_BLOCK);
				}
			} else {
				continue;
			}
		}
	}
	
	private Block getBlockInDirection(Block block, String direction) {
		Location loc = block.getLocation();
		switch(direction) {
			case "NORTH":
				loc.setZ(loc.getZ() - 1);
				break;
			case "EAST":
				loc.setX(loc.getX() + 1);
				break;
			case "SOUTH":
				loc.setZ(loc.getZ() + 1);
				break;
			case "WEST":
				loc.setX(loc.getX() - 1);
				break;
			default:
				break;
		}
		return loc.getBlock();
		
	}
}
