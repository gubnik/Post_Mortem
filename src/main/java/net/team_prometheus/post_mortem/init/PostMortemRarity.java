package net.team_prometheus.post_mortem.init;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

public class PostMortemRarity{
    public static Rarity BloodRarity() {
        Rarity blood = Rarity.create("BLOOD", ChatFormatting.DARK_RED);
        return blood;
    }
    public static Rarity SoulflameRarity() {
        Rarity soulflame = Rarity.create("BLOOD", ChatFormatting.DARK_AQUA);
        return soulflame;
    }
}
