package net.team_prometheus.post_mortem.init;


import net.team_prometheus.post_mortem.particle.DropOfBlood;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModParticles {
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.register(ParticleTypes.DROP_OF_BLOOD.get(), DropOfBlood::provider);
    }
}
