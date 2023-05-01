package net.team_prometheus.post_mortem.init;

import net.team_prometheus.post_mortem.Post_Mortem;
import net.team_prometheus.post_mortem.potion.Bleeding;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

public class MobEffects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Post_Mortem.MOD_ID);
    public static final RegistryObject<MobEffect> BLEEDING = REGISTRY.register("bleeding", () -> new Bleeding());
}

