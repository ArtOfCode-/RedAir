package uk.co.riversparrow.redair.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class HelpCommand {
	public static void execute(CommandSender sender) {
		if(!sender.hasPermission("redair.use")) {
			sender.sendMessage(ChatColor.RED + "You don't have permission to run this command!");
			return;
		}
		sender.sendMessage(ChatColor.DARK_RED + "============= "
				+ ChatColor.AQUA + "RedAir Help" + ChatColor.DARK_RED
				+ " =============");
		sender.sendMessage(ChatColor.GREEN + "/redair help" + ChatColor.AQUA
				+ " - shows this help message.");
		sender.sendMessage(ChatColor.GREEN
				+ "/redair start"
				+ ChatColor.AQUA
				+ " - sets the block you're pointing at as the start block of a new wireless connection.");
		sender.sendMessage(ChatColor.GREEN
				+ "/redair end"
				+ ChatColor.AQUA
				+ " - sets the block you're pointing at as the end block of a new wireless connection.");
		sender.sendMessage(ChatColor.GREEN
				+ "/redair remove"
				+ ChatColor.AQUA
				+ " - remove the block you're pointing at from a wireless connection.");
	}
}
