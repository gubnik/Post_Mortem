package net.team_prometheus.post_mortem.init;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

public class PostMortemRarity{
    public static Rarity BloodRarity() {
        return Rarity.create("BLOOD", ChatFormatting.DARK_RED);
    }
    public static Rarity SoulflameRarity() {
        return Rarity.create("BLOOD", ChatFormatting.DARK_AQUA);
    }
}
