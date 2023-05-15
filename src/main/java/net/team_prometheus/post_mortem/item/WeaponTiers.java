package net.team_prometheus.post_mortem.item;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public enum WeaponTiers implements Tier {
    BLOOD(3, 1240, 7.0F, 0.0F, 17, () -> {
        return Ingredient.of(ModItems.BLOODSTAINED_INGOT.get());
    }),
    SOULFLAME(3, 1440, 9.0F, 0.0F, 19, () -> {
        return Ingredient.of(ModItems.BLOODSTAINED_INGOT.get());
    });

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    WeaponTiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> ingredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(ingredient);
    }
    public int getUses() {
        return this.uses;
    }
    public float getSpeed() {
        return this.speed;
    }
    public float getAttackDamageBonus() {
        return this.damage;
    }
    public int getLevel() {
        return this.level;
    }
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
