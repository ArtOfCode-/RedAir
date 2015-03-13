package uk.co.riversparrow.redair.command;

import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
		HashSet<Material> transparentBlocks = new HashSet<Material>();
		transparentBlocks.add(Material.AIR);
		transparentBlocks.add(Material.REDSTONE_WIRE);
		Block targetBlock = player.getTargetBlock(transparentBlocks, 200);
		if(!Cache.isInMaps(targetBlock)) {
			player.sendMessage(ChatColor.RED + "Target block is not part of a connection or not the first block.");
			return;
		}
		Cache.removeMapping(targetBlock);
		player.sendMessage(ChatColor.GREEN + "Connection successfully removed.");
	}
}
