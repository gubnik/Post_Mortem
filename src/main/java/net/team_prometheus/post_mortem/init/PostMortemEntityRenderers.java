package net.team_prometheus.post_mortem.init;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.RegistryObject;
import net.team_prometheus.post_mortem.models.AngrySpiritModel;
import net.team_prometheus.post_mortem.renderer.AngrySpiritRenderer;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import java.util.Collection;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PostMortemEntityRenderers {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.ANGRY_SPIRIT.get(), AngrySpiritRenderer::new);
    }
    @SubscribeEvent
    public void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AngrySpiritModel.LAYER_LOCATION, AngrySpiritModel::createBodyLayer);
    }
    public static void globalEntityRenderingRegistrar(Collection<RegistryObject<EntityType<?>>> entities) {
        EntityRenderers.register(ModEntities.ANGRY_SPIRIT.get(), AngrySpiritRenderer::new);
    }
}