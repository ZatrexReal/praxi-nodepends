package lol.vifez.praxi.commands.user.party;

import lol.vifez.praxi.profile.Profile;
import lol.vifez.praxi.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = { "party chat", "p chat" })
public class PartyChatCommand {

	public void execute(Player player, String message) {
		Profile profile = Profile.getByUuid(player.getUniqueId());

		if (profile.getParty() != null) {
			profile.getParty().sendChat(player, message);
		}
	}

}
