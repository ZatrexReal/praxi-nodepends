package lol.vifez.praxi.commands.admin.arena;

import lol.vifez.praxi.arena.selection.Selection;
import lol.vifez.praxi.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = { "arena wand", "arena selection" }, permission = "praxi.admin.arena")
public class ArenaSelectionCommand {

	public void execute(Player player) {
		if (player.getInventory().first(Selection.SELECTION_WAND) != -1) {
			player.getInventory().remove(Selection.SELECTION_WAND);
		} else {
			player.getInventory().addItem(Selection.SELECTION_WAND);
		}

		player.updateInventory();
	}

}
