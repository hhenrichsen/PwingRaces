package net.pwing.races.race.trigger;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

public class RaceTrigger {

	private String regName;
	private String requirement;
	private String trigger;

	private int delay;
	private int chance;
	private List<String> passives = new ArrayList<String>();

	public RaceTrigger(String regName, String configPath, YamlConfiguration config, String requirement) {
		this.regName = regName;
		this.requirement = requirement;

		loadDataFromConfig(configPath, config);
	}

	public void loadDataFromConfig(String configPath, YamlConfiguration config) {
		this.trigger = config.getString(configPath + ".trigger");
		this.delay = config.getInt(configPath + ".delay");
		this.chance = config.getInt(configPath + ".chance");
		this.passives = config.getStringList(configPath + ".run-passives");
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getChance() {
		return chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}

	public List<String> getPassives() {
		return passives;
	}

	public void setPassives(List<String> passives) {
		this.passives = passives;
	}
}
