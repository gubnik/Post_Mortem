package net.team_prometheus.post_mortem.init;

import net.minecraft.world.damagesource.DamageSource;

public class PostMortemDamageSource{
    public static final DamageSource BLEED = (new DamageSource("bleed")).bypassArmor().bypassEnchantments().bypassMagic().bypassInvul();
    public static final DamageSource SOULFLAME = (new DamageSource("soulflame")).bypassEnchantments().bypassMagic();
}
