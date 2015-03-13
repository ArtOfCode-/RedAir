package uk.co.riversparrow.redair.command;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import uk.co.riversparrow.redair.Cache;

public class StartCommand {
	public static void execute(CommandSender sender) {
		if(!sender.hasPermission("redair.create")) {
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
		String message = ChatColor.GREEN
				+ "Started a new connection"
				+ (Cache.firstBlocks.containsKey(player) ? ", overwriting an old start block."
						: ".");
		Cache.firstBlocks.put(player, targetBlock);
		player.sendMessage(message);
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
