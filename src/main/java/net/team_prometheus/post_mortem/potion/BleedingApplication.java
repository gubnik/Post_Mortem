package net.team_prometheus.post_mortem.potion;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.team_prometheus.post_mortem.init.PostMortemAttributes;
import net.team_prometheus.post_mortem.init.PostMortemEffects;
import net.team_prometheus.post_mortem.init.PostMortemGameRules;
import net.team_prometheus.post_mortem.item.ModItems;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Objects;

@Mod.EventBusSubscriber
public class BleedingApplication {
    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event.getEntity().level, event.getEntity(), event.getSource().getDirectEntity(), event.getAmount());
        }
    }
    public static void execute(LevelAccessor world, Entity entity, Entity immediatesourceentity, double amount) {
        if (entity == null || immediatesourceentity == null)
            return;
        double lvl;
        double time;
        if(immediatesourceentity instanceof LivingEntity source && entity instanceof LivingEntity target)
            // bleed weapons
            if (!entity.isInvulnerable() && !(target.isBlocking()) && entity.isAlive()) {
                ItemStack itemStack = source.getMainHandItem();
                double modifier = itemStack.getAttributeModifiers(EquipmentSlot.MAINHAND).get(PostMortemAttributes.BLEED_APPLICATION.get()).stream().mapToDouble(AttributeModifier::getAmount).sum();
                if (modifier > Math.random()) {
                    if (amount >= 2) {
                        target.addEffect(new MobEffectInstance(PostMortemEffects.BLEEDING.get(), 100,
                            ((target.hasEffect(PostMortemEffects.BLEEDING.get()) ? Objects.requireNonNull(target.getEffect(PostMortemEffects.BLEEDING.get())).getAmplifier() : 0) + (int)Math.ceil(modifier))));
                }
            }
            if ((target.hasEffect(PostMortemEffects.BLEEDING.get()) ? Objects.requireNonNull(target.getEffect(PostMortemEffects.BLEEDING.get())).getAmplifier() : 0) == (world.getLevelData().getGameRules()
                    .getInt(PostMortemGameRules.BLEEDING_ACTIVATION))) {
                if (CuriosApi.getCuriosHelper().findFirstCurio(source, ModItems.BLOOD_PACT.get()).isPresent()) {
                    if (!source.hasEffect(MobEffects.DAMAGE_BOOST) || (source.hasEffect(MobEffects.DAMAGE_BOOST) ? Objects.requireNonNull(source.getEffect(MobEffects.DAMAGE_BOOST)).getAmplifier() : 0) <= 1) {
                        source.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1, (false), (true)));
                    } else {
                        lvl = source.hasEffect(MobEffects.DAMAGE_BOOST) ? Objects.requireNonNull(source.getEffect(MobEffects.DAMAGE_BOOST)).getAmplifier() : 0;
                        time = source.hasEffect(MobEffects.DAMAGE_BOOST) ? Objects.requireNonNull(source.getEffect(MobEffects.DAMAGE_BOOST)).getDuration() : 0;
                        source.removeEffect(MobEffects.DAMAGE_BOOST);
                        source.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, (int) (time + 200), (int) lvl, (false), (true)));
                    }
                }
            } // blood pact
        }
    }
}