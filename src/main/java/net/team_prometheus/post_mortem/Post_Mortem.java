package net.team_prometheus.post_mortem;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.team_prometheus.post_mortem.init.*;
import net.team_prometheus.post_mortem.item.ModItems;
import net.team_prometheus.post_mortem.models.AngrySpiritModel;
import net.team_prometheus.post_mortem.renderer.AngrySpiritRenderer;
import org.slf4j.Logger;
import net.minecraftforge.client.event.EntityRenderersEvent;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@Mod(Post_Mortem.MOD_ID)
public class Post_Mortem
{
    public static final String MOD_ID = "post_mortem";
    public static final UUID BLEED_APPLICATION = UUID.fromString("19d48280-f359-11ed-a05b-0242ac120003");
    private static final Logger LOGGER = LogUtils.getLogger();
    public Post_Mortem() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        PostMortemAttributes.ATTRIBUTES.register(modEventBus);
        PostMortemEffects.REGISTRY.register(modEventBus);
        ParticleTypes.REGISTRY.register(modEventBus);
        PostMortemEnchantments.REGISTRY.register(modEventBus);
        PostMortemSounds.REGISTRY.register(modEventBus);
        ModEntities.REGISTRY.register(modEventBus);
        modEventBus.addListener(this::setupClient);
        modEventBus.addListener(this::registerLayerDefinitions);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    // I shall have a look on this later
    private void commonSetup(final FMLCommonSetupEvent event) {
    }
    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();
    public static void queueServerWork(int tick, Runnable action) {
        workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
    }
    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
            workQueue.forEach(work -> {
                work.setValue(work.getValue() - 1);
                if (work.getValue() == 0)
                    actions.add(work);
            });
            actions.forEach(e -> e.getKey().run());
            workQueue.removeAll(actions);
        }
    }
    //
    private void setupClient(final FMLCommonSetupEvent event) {
        EntityRenderers.register(ModEntities.ANGRY_SPIRIT.get(), AngrySpiritRenderer::new);
    }
    private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AngrySpiritModel.LAYER_LOCATION, AngrySpiritModel::createBodyLayer);
    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
