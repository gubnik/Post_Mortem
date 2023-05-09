package net.team_prometheus.post_mortem.item.effects;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.team_prometheus.post_mortem.init.PostMortemEffects;
import net.team_prometheus.post_mortem.item.ModItems;
import java.util.Objects;

@Mod.EventBusSubscriber
public class BloodVialEffect {
    @SubscribeEvent
    public static void onUseItemFinish(LivingEntityUseItemEvent.Finish event) {
        if (event != null && event.getEntity() != null && event.getItem() != null) {
            ItemStack itemStack = event.getItem();
            LivingEntity entity = event.getEntity();
            LevelAccessor world = event.getEntity().getLevel();
            double x = event.getEntity().getX();
            double y = event.getEntity().getY();
            double z = event.getEntity().getZ();
            if (itemStack.getItem() == ModItems.BLOOD_VIAL.get()) {
                entity.addEffect(new MobEffectInstance(PostMortemEffects.BLEEDING.get(), 100, entity.hasEffect(PostMortemEffects.BLEEDING.get()) ? Objects.requireNonNull(entity.getEffect(PostMortemEffects.BLEEDING.get())).getAmplifier() + 1 : 0));
                entity.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 1));
                Level level = (Level) world;
                if (!level.isClientSide()) {
                    level.playSound(null, new BlockPos(x, y, z), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.glass.break"))), SoundSource.PLAYERS, (float) 0.6, 1);
                } else {
                    level.playLocalSound(x, y, z, Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.glass.break"))), SoundSource.PLAYERS, (float) 0.6, 1, false);
                }
            }
        }
    }
}