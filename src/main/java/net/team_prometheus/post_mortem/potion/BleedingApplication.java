package net.team_prometheus.post_mortem.potion;

import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.Event;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.team_prometheus.post_mortem.init.PostMortemEffects;
import net.team_prometheus.post_mortem.init.PostMortemEnchantments;
import net.team_prometheus.post_mortem.init.PostMortemGamerules;
import net.team_prometheus.post_mortem.item.ModItems;
import top.theillusivec4.curios.api.CuriosApi;
import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BleedingApplication {
    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event, event.getEntity().level, event.getEntity(), event.getSource().getDirectEntity(), event.getAmount());
        }
    }

    public static void execute(LevelAccessor world, Entity entity, Entity immediatesourceentity, double amount) {
        execute(null, world, entity, immediatesourceentity, amount);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity immediatesourceentity, double amount) {
        if (entity == null || immediatesourceentity == null)
            return;
        double lvl = 0;
        double time = 0;
        if (!entity.isInvulnerable() && !(entity instanceof LivingEntity _livEnt ? _livEnt.isBlocking() : false) && entity.isAlive()) {
            if (Math.random() < EnchantmentHelper.getItemEnchantmentLevel(PostMortemEnchantments.RUPTURE.get(), (immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) * 0.2) {
                if (entity instanceof LivingEntity _entity)
                    _entity.addEffect(new MobEffectInstance(PostMortemEffects.BLEEDING.get(), 100,
                            (int) ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(PostMortemEffects.BLEEDING.get()) ? _livEnt.getEffect(PostMortemEffects.BLEEDING.get()).getAmplifier() : 0)
                                    + (world.getLevelData().getGameRules().getInt(PostMortemGamerules.DEFAULT_BLEEDING_APPLICATION)))));
            }
            if ((immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("forge:bleed_weapons")))) {
                if (amount >= 3) {
                    if (entity instanceof LivingEntity _entity)
                        _entity.addEffect(new MobEffectInstance(PostMortemEffects.BLEEDING.get(), 100,
                                (int) ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(PostMortemEffects.BLEEDING.get()) ? _livEnt.getEffect(PostMortemEffects.BLEEDING.get()).getAmplifier() : 0)
                                        + (world.getLevelData().getGameRules().getInt(PostMortemGamerules.DEFAULT_BLEEDING_APPLICATION)))));
                }
            }
            if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(PostMortemEffects.BLEEDING.get()) ? _livEnt.getEffect(PostMortemEffects.BLEEDING.get()).getAmplifier() : 0) == (world.getLevelData().getGameRules()
                    .getInt(PostMortemGamerules.BLEEDING_ACTIVATION))) {
                if (immediatesourceentity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(ModItems.BLOOD_PACT.get(), lv).isPresent() : false) {
                    if (!(immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(MobEffects.DAMAGE_BOOST) : false)
                            || (immediatesourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.DAMAGE_BOOST) ? _livEnt.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() : 0) <= 1) {
                        if (immediatesourceentity instanceof LivingEntity _entity)
                            _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1, (false), (true)));
                    } else {
                        lvl = immediatesourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.DAMAGE_BOOST) ? _livEnt.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() : 0;
                        time = immediatesourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.DAMAGE_BOOST) ? _livEnt.getEffect(MobEffects.DAMAGE_BOOST).getDuration() : 0;
                        if (immediatesourceentity instanceof LivingEntity _entity)
                            _entity.removeEffect(MobEffects.DAMAGE_BOOST);
                        if (immediatesourceentity instanceof LivingEntity _entity)
                            _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, (int) (time + 200), (int) lvl, (false), (true)));
                    }
                }
            }
        }
    }
}

