package net.pwing.races.compat;

import net.pwing.races.PwingRaces;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class CompatCodeHandler_v1_13_R2 extends CompatCodeHandlerDisabled {

	private PwingRaces plugin;

	public CompatCodeHandler_v1_13_R2(PwingRaces plugin) {
		super(plugin);

		this.plugin = plugin;
	}

	@Override
	public int getDamage(ItemStack item) {
		if (item.getItemMeta() instanceof Damageable)
			return ((Damageable) item.getItemMeta()).getDamage();

		return 0;
	}

	@Override
	public void setDamage(ItemStack item, int damage) {
		ItemMeta meta = item.getItemMeta();

		if (item.getItemMeta() instanceof Damageable) {
			Damageable damageMeta = (Damageable) meta;
			damageMeta.setDamage(damage);
			item.setItemMeta(meta);
		}
	}

	@Override
	public void setUnbreakable(ItemStack item, boolean unbreakable) {
		ItemMeta meta = item.getItemMeta();
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
	}

	@Override
	public void setColor(ItemStack item, Color color) {
		super.setColor(item, color);

		if (item.getItemMeta() instanceof PotionMeta) {
			PotionMeta im = (PotionMeta) item.getItemMeta();
			im.setColor(color);
			item.setItemMeta(im);
		}
	}

	@Override
	public Enchantment getEnchantment(String name) {
		return EnchantmentWrapper.getByKey(NamespacedKey.minecraft(name.toLowerCase()));
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setOwner(ItemStack item, String owner) {
		if (item.getItemMeta() instanceof SkullMeta) {
			SkullMeta meta = (SkullMeta) item.getItemMeta();
			meta.setOwningPlayer(Bukkit.getOfflinePlayer(owner));
			item.setItemMeta(meta);
		}
	}

	@Override
	public double getMaxHealth(Player player) {
		return player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
	}

	@Override
	public void setMaxHealth(Player player, double maxHealth) {
		player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
	}

	@Override
	public ItemStack getItemInMainHand(Player player) {
		return player.getInventory().getItemInMainHand();
	}

	@Override
	public boolean isBukkitAttribute(String attribute) {
		try {
			Attribute.valueOf(attribute);
			return true;
		} catch (IllegalArgumentException ex) {/* do nothing */}

		return false;
	}

	@Override
	public String getAttributeName(String str) {
		str = str.toUpperCase().replace("-", "_");

		String bukkitAttribute = str;
		if (!bukkitAttribute.startsWith("GENERIC_"))
			bukkitAttribute = "GENERIC_" + str;

		try {
			Attribute.valueOf(bukkitAttribute);
			return bukkitAttribute;
		} catch (Exception ex) {/* do nothing */}

		return str;
	}

	@Override
	public double getAttributeValue(Player player, String attribute) {
		if (!isBukkitAttribute(attribute))
			return 0;

		String attributeName = getAttributeName(attribute);
		return player.getAttribute(Attribute.valueOf(attributeName)).getBaseValue();
	}

	@Override
	public double getDefaultAttributeValue(Player player, String attribute) {
		if (!isBukkitAttribute(attribute))
			return 0;

		String attributeName = getAttributeName(attribute);
		return player.getAttribute(Attribute.valueOf(attributeName)).getDefaultValue();
	}

	@Override
	public void setAttributeValue(Player player, String attribute, double amount) {
		if (!isBukkitAttribute(attribute))
			return;

		String attributeName = getAttributeName(attribute);
		player.getAttribute(Attribute.valueOf(attributeName)).setBaseValue(amount);
	}

	@Override
	public double getDamage(Arrow arrow) {
		return arrow.getDamage();
	}

	@Override
	public void setPickupStatus(Arrow arrow, String status) {
		try {
			arrow.setPickupStatus(Arrow.PickupStatus.valueOf(status.toUpperCase()));
		} catch (IllegalArgumentException ex) { /* do nothing */ }
	}

	@Override
	public void setDamage(Arrow arrow, double damage) {
		arrow.setDamage(damage);
	}
}
