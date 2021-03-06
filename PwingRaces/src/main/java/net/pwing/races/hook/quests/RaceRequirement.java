package net.pwing.races.hook.quests;

import java.util.Map;

import me.blackvein.quests.CustomRequirement;
import net.pwing.races.api.race.Race;
import net.pwing.races.api.race.RaceManager;
import net.pwing.races.api.race.RacePlayer;
import org.bukkit.entity.Player;

public class RaceRequirement extends CustomRequirement {

    private RaceManager raceManager;

    public RaceRequirement(RaceManager raceManager) {
        this.raceManager = raceManager;

        setName("Race Requirement");
        setAuthor("Redned");
        addStringPrompt("Race", "Enter the race requirement.", null);
    }

    @Override
    public boolean testRequirement(Player player, Map<String, Object> data) {
        String raceStr = (String) data.get("Race");

        if (!raceManager.getRaceFromName(raceStr).isPresent())
            return false;

        Race race = raceManager.getRaceFromName(raceStr).get();
        RacePlayer racePlayer = raceManager.getRacePlayer(player);
        if (!racePlayer.getRace().isPresent())
            return false;

        return racePlayer.getRace().get().getName().equals(race.getName());
    }
}
