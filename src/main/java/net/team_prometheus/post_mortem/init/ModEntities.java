package net.team_prometheus.post_mortem.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.team_prometheus.post_mortem.Post_Mortem;
import net.team_prometheus.post_mortem.entity.AngrySpirit;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Post_Mortem.MOD_ID);
    public static final RegistryObject<EntityType<AngrySpirit>> ANGRY_SPIRIT = register(EntityType.Builder.<AngrySpirit>of(AngrySpirit::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
            .setTrackingRange(64).setUpdateInterval(3).sized(0.6f, 1.8f));
    private static <T extends Entity> RegistryObject<EntityType<T>> register(EntityType.Builder<T> entityTypeBuilder) {
        return REGISTRY.register("angry_spirit", () -> (EntityType<T>) entityTypeBuilder.build("angry_spirit"));
    }
    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(AngrySpirit::init);
    }
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ANGRY_SPIRIT.get(), AngrySpirit.createAttributes().build());
    }
}
