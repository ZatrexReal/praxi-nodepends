package lol.vifez.praxi.util.command.command.adapter.impl;

import lol.vifez.praxi.util.command.command.adapter.CommandTypeAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerTypeAdapter implements CommandTypeAdapter
{
    @Override
    public <T> T convert(final String string, final Class<T> type) {
        return type.cast(Bukkit.getPlayer(string));
    }
    
    @Override
    public <T> List<String> tabComplete(final String string, final Class<T> type) {
        final List<String> completed = new ArrayList<String>();
        for (final Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().toLowerCase().startsWith(string.toLowerCase())) {
                completed.add(player.getName());
            }
        }
        return completed;
    }
}
