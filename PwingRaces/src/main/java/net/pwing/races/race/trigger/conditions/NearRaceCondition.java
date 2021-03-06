package net.pwing.races.race.trigger.conditions;

import lombok.AllArgsConstructor;

import net.pwing.races.PwingRaces;
import net.pwing.races.api.race.Race;
import net.pwing.races.api.race.RaceManager;
import net.pwing.races.api.race.trigger.condition.RaceCondition;
import net.pwing.races.util.RaceUtil;
import net.pwing.races.util.math.NumberUtil;

import org.bukkit.entity.Player;

import java.util.Optional;

@AllArgsConstructor
public class NearRaceCondition implements RaceCondition {

    private PwingRaces plugin;

    @Override
    public boolean check(Player player, String[] args) {
        if (args.length < 4)
            return false;

        Optional<Race> race = plugin.getRaceManager().getRaceFromName(args[1]);
        if (!race.isPresent())
            return false;

        double radius = NumberUtil.getDouble(args[2]);
        int requiredNearby = NumberUtil.getInteger(args[3]);
        return RaceUtil.getNearbyRaceCount(player.getLocation(), race.get(), radius) >= requiredNearby;
    }
}
