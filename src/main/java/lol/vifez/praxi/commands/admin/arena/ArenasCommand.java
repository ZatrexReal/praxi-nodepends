package lol.vifez.praxi.commands.admin.arena;

import lol.vifez.praxi.arena.Arena;
import lol.vifez.praxi.arena.ArenaType;
import lol.vifez.praxi.util.CC;
import lol.vifez.praxi.util.ChatComponentBuilder;
import lol.vifez.praxi.util.ChatHelper;
import lol.vifez.praxi.util.command.command.CommandMeta;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

@CommandMeta(label = "arenas", permission = "praxi.admin.arena")
public class ArenasCommand {

	public void execute(Player player) {
		player.sendMessage(CC.GOLD + "Arenas:");

		if (Arena.getArenas().isEmpty()) {
			player.sendMessage(CC.GRAY + "There are no arenas.");
			return;
		}

		for (Arena arena : Arena.getArenas()) {
			if (arena.getType() != ArenaType.DUPLICATE) {
				ChatComponentBuilder builder = new ChatComponentBuilder("")
						.parse("&7- " + (arena.isSetup() ? "&a" : "&c") + arena.getName() +
						       "&7(" + arena.getType().name() + ") ");

				ChatComponentBuilder status = new ChatComponentBuilder("").parse("&7[&6STATUS&7]");
				status.attachToEachPart(ChatHelper.hover("&6Click to view this arena's status."));
				status.attachToEachPart(ChatHelper.click("/arena status " + arena.getName()));

				builder.append(" ");

				for (BaseComponent component : status.create()) {
					builder.append((TextComponent) component);
				}

				player.spigot().sendMessage(builder.create());
			}
		}
	}

}
