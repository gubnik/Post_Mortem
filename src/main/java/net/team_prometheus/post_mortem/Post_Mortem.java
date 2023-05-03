package net.team_prometheus.post_mortem;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.team_prometheus.post_mortem.init.PostMortemEffects;
import net.team_prometheus.post_mortem.init.ParticleTypes;
import net.team_prometheus.post_mortem.init.PostMortemEnchantments;
import net.team_prometheus.post_mortem.item.ModItems;
import org.slf4j.Logger;

@Mod(Post_Mortem.MOD_ID)
public class Post_Mortem
{
    public static final String MOD_ID = "post_mortem";
    private static final Logger LOGGER = LogUtils.getLogger();
    public Post_Mortem() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        PostMortemEffects.REGISTRY.register(modEventBus);
        ParticleTypes.REGISTRY.register(modEventBus);
        PostMortemEnchantments.REGISTRY.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
