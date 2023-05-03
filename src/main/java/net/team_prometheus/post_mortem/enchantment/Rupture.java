package net.team_prometheus.post_mortem.enchantment;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.team_prometheus.post_mortem.init.MobEffects;

public class Rupture extends Enchantment {
    public Rupture(EquipmentSlot... slots) {
        super(Enchantment.Rarity.COMMON, EnchantmentCategory.WEAPON, slots);
    }
    @Override
    public int getMaxLevel(){
        return 5;
    }
}
