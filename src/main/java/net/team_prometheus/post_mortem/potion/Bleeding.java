package net.team_prometheus.post_mortem.potion;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.effect.MobEffect;

import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;


public class Bleeding extends MobEffect {
    public Bleeding() {
        super(MobEffectCategory.HARMFUL, -6999483);
    }

    @Override
    public String getDescriptionId() {
        return "effect.post_mortem.bleeding";
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        BleedingEffect.execute(entity.level, entity.getX(), entity.getY(), entity.getZ(), entity);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void initializeClient(java.util.function.Consumer<IClientMobEffectExtensions> consumer) {
        consumer.accept(new IClientMobEffectExtensions() {
            @Override
            public boolean isVisibleInInventory(MobEffectInstance effect) {
                return true;
            }

            @Override
            public boolean renderInventoryText(MobEffectInstance instance, EffectRenderingInventoryScreen<?> screen, PoseStack poseStack, int x, int y, int blitOffset) {
                return true;
            }
        });
    }

}
