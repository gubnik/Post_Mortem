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

    public static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity immediatesourceentity, double amount) {
        if (entity == null || immediatesourceentity == null)
            return;
        double lvl = 0;
        double time = 0;
        LivingEntity _livEnt = (LivingEntity) immediatesourceentity;
        LivingEntity _entity = (LivingEntity) entity;
        if (!entity.isInvulnerable() && !(_entity.isBlocking()) && entity.isAlive()) {
            if (Math.random() < EnchantmentHelper.getItemEnchantmentLevel(PostMortemEnchantments.RUPTURE.get(), _livEnt.getMainHandItem()) * 0.2) {
                _entity.addEffect(new MobEffectInstance(PostMortemEffects.BLEEDING.get(), 100,
                        (int) ((_entity.hasEffect(PostMortemEffects.BLEEDING.get()) ? _entity.getEffect(PostMortemEffects.BLEEDING.get()).getAmplifier() : 0) + 1)));
            }
            if (_livEnt.getMainHandItem().is(ItemTags.create(new ResourceLocation("forge:bleed_weapons")))) {
                if (amount >= 3) {
                    _entity.addEffect(new MobEffectInstance(PostMortemEffects.BLEEDING.get(), 100,
                            (int) ((_entity.hasEffect(PostMortemEffects.BLEEDING.get()) ? _entity.getEffect(PostMortemEffects.BLEEDING.get()).getAmplifier() : 0) + 1)));
                }
            }
            if ((_entity.hasEffect(PostMortemEffects.BLEEDING.get()) ? _entity.getEffect(PostMortemEffects.BLEEDING.get()).getAmplifier() : 0) == (world.getLevelData().getGameRules()
                    .getInt(PostMortemGamerules.BLEEDING_ACTIVATION))) {
                if (CuriosApi.getCuriosHelper().findFirstCurio(_livEnt, ModItems.BLOOD_PACT.get()).isPresent()) {
                    if (!_livEnt.hasEffect(MobEffects.DAMAGE_BOOST) || (_livEnt.hasEffect(MobEffects.DAMAGE_BOOST) ? _livEnt.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() : 0) <= 1) {
                        _livEnt.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1, (false), (true)));
                    } else {
                        lvl = _livEnt.hasEffect(MobEffects.DAMAGE_BOOST) ? _livEnt.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() : 0;
                        time = _livEnt.hasEffect(MobEffects.DAMAGE_BOOST) ? _livEnt.getEffect(MobEffects.DAMAGE_BOOST).getDuration() : 0;
                        _livEnt.removeEffect(MobEffects.DAMAGE_BOOST);
                        _livEnt.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, (int) (time + 200), (int) lvl, (false), (true)));
                    }
                }
            }
        }
    }
}
