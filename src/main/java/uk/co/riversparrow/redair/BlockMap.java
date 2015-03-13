package uk.co.riversparrow.redair;

import org.bukkit.World;
import org.bukkit.block.Block;

public class BlockMap {
	private Block block;
	private World world;
	
	public BlockMap(World thisWorld, Block thisBlock) {
		block = thisBlock;
		world = thisWorld;
	}
	public BlockMap(String blockMapString, Utils utils) throws IllegalArgumentException {
		try {
			blockMapString = blockMapString.trim();
			blockMapString = blockMapString.replace("{", "");
			blockMapString = blockMapString.replace("}", "");
			String[] parts = blockMapString.split(";");
			String worldName = parts[0].split(":")[1];
			String coordString = parts[1].split(":")[1];
			world = utils.getWorld(worldName);
			Coordinates coords = new Coordinates(coordString);
			block = utils.getBlockAtCoords(coords, world);
		} catch(Exception ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}
	
	@Override
	public String toString() {
		Coordinates coords = new Coordinates(block.getX(), block.getY(), block.getZ());
		return "{World:" + world.getName() + ";Block:" + coords.toString() + "}";
	}
	
	public World getWorld() {
		return world;
	}
	
	public Block getBlock() {
		return block;
	}
}
