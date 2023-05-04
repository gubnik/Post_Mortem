package net.team_prometheus.post_mortem.init;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.team_prometheus.post_mortem.Post_Mortem;

public class PostMortemRarity{
    public static Rarity BloodRarity() {
        return Rarity.create("BLOOD", ChatFormatting.DARK_RED);
    }
    public static Rarity SoulflameRarity() {
        return Rarity.create("SOULFLAME", ChatFormatting.DARK_AQUA);
    }
}
