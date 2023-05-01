package net.team_prometheus.post_mortem.init;

import net.team_prometheus.post_mortem.Post_Mortem;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

public class ParticleTypes {
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Post_Mortem.MOD_ID);
    public static final RegistryObject<SimpleParticleType> DROP_OF_BLOOD = REGISTRY.register("drop_of_blood", () -> new SimpleParticleType(true));

}
