package lol.vifez.praxi.commands.event.user;

import lol.vifez.praxi.event.game.EventGame;
import lol.vifez.praxi.util.command.command.CommandMeta;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "event cancel", permission = "praxi.admin.event")
public class EventCancelCommand {

	public void execute(CommandSender sender) {
		if (EventGame.getActiveGame() != null) {
			EventGame.getActiveGame().getGameLogic().cancelEvent();
		} else {
			sender.sendMessage(ChatColor.RED + "There is no active event.");
		}
	}

}
