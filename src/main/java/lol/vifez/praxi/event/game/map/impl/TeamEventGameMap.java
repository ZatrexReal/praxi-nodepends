package lol.vifez.praxi.event.game.map.impl;

import lol.vifez.praxi.Praxi;
import lol.vifez.praxi.event.game.EventGame;
import lol.vifez.praxi.event.game.map.EventGameMap;
import lol.vifez.praxi.event.impl.sumo.SumoGameLogic;
import lol.vifez.praxi.participant.GameParticipant;
import lol.vifez.praxi.participant.GamePlayer;
import lol.vifez.praxi.util.LocationUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class TeamEventGameMap extends EventGameMap {

	@Getter @Setter private Location spawnPointA;
	@Getter @Setter private Location spawnPointB;

	public TeamEventGameMap(String mapName) {
		super(mapName);
	}

	@Override
	public void teleportFighters(EventGame game) {
		int locationIndex = 0;
		Location[] locations = new Location[]{ spawnPointA, spawnPointB };

		if (game.getGameLogic() instanceof SumoGameLogic) {
			GameParticipant<? extends GamePlayer>[] participants = new GameParticipant[] {
					((SumoGameLogic) game.getGameLogic()).getParticipantA(),
					((SumoGameLogic) game.getGameLogic()).getParticipantB()
			};

			for (GameParticipant<? extends GamePlayer> participant : participants) {
				int processed = 0;

				for (GamePlayer gamePlayer : participant.getPlayers()) {
					processed++;

					Player player = gamePlayer.getPlayer();

					if (player != null) {
						player.teleport(locations[locationIndex]);
					}

					if (processed == participant.getPlayers().size()) {
						locationIndex++;
					}
				}
			}
		}
	}

	@Override
	public boolean isSetup() {
		return spectatorPoint != null && spawnPointA != null && spawnPointB != null;
	}

	@Override
	public void save() {
		super.save();

		FileConfiguration config = Praxi.get().getEventsConfig().getConfiguration();
		config.set("EVENT_MAPS." + getMapName() + ".TYPE", "TEAM");
		config.set("EVENT_MAPS." + getMapName() + ".SPAWN_POINT_A", LocationUtil.serialize(spawnPointA));
		config.set("EVENT_MAPS." + getMapName() + ".SPAWN_POINT_B", LocationUtil.serialize(spawnPointB));

		try {
			config.save(Praxi.get().getEventsConfig().getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
