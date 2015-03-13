package uk.co.riversparrow.redair.command;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import uk.co.riversparrow.redair.Cache;

public class RemoveCommand {
	public static void execute(CommandSender sender) {
		if(!sender.hasPermission("redair.remove")) {
			sender.sendMessage(ChatColor.RED + "You don't have permission to run this command!");
			return;
		}
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED
					+ "You must be a player to run this command!");
			return;
		}
		Player player = (Player) sender;
		Block targetBlock = getTargetBlock(player, 200);
		if(!Cache.isInMaps(targetBlock)) {
			player.sendMessage(ChatColor.RED + "Target block is not part of a connection or not the first block.");
			return;
		}
		Cache.removeMapping(targetBlock);
		player.sendMessage(ChatColor.GREEN + "Connection successfully removed.");
	}
	
	private static Block getTargetBlock(Player player, int maxDistance) {
		// Location loc = player.getLocation();
		// BlockIterator blockIter = new BlockIterator(loc.getWorld(), loc.toVector(), loc.getDirection(), 0d, maxDistance);
		BlockIterator blockIter = new BlockIterator(player, maxDistance);
		Block target = null;
		while(blockIter.hasNext()) {
			target = blockIter.next();
			Material type = target.getType();
			if(type != Material.AIR && type != Material.REDSTONE_WIRE) {
				break;
			}
		}
		return target;
	}
}
