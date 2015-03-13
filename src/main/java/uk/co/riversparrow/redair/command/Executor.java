package uk.co.riversparrow.redair.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Executor implements CommandExecutor {
	public Executor() {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		List<String> arguments = new ArrayList<String>(Arrays.asList(args));
		if(arguments.size() > 0) {
			arguments.remove(0);
		}
		if (args.length == 0) {
			HelpCommand.execute(sender);
			return true;
		}
		switch (args[0]) {
		case "help":
			HelpCommand.execute(sender);
			break;
		case "start":
			StartCommand.execute(sender);
			break;
		case "end":
			EndCommand.execute(sender);
			break;
		case "remove":
			RemoveCommand.execute(sender);
			break;
		default:
			HelpCommand.execute(sender);
			break;
		}
		return true;
	}
}
