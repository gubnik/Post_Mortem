package net.team_prometheus.post_mortem.potion;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;
import net.team_prometheus.post_mortem.init.PostMortemGamerules;
import net.team_prometheus.post_mortem.init.PostMortemEffects;

import net.minecraft.world.entity.Entity;
import net.team_prometheus.post_mortem.init.ParticleTypes;


public class BleedingEffect {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
    double k = 0;
    if (!(entity instanceof LivingEntity _livEnt && _livEnt.getMobType() == MobType.UNDEAD) && !world.getLevelData().getGameRules().getBoolean(PostMortemGamerules.UNIVERSAL_BLEEDING)
        && !entity.getType().is(TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("forge:bleeding_immune"))) || world.getLevelData().getGameRules().getBoolean(PostMortemGamerules.UNIVERSAL_BLEEDING)) {
        if (world instanceof ServerLevel _level)
            _level.sendParticles((SimpleParticleType) (ParticleTypes.DROP_OF_BLOOD.get()), x, (y + entity.getBbHeight() / 2), z,
                    (int) (((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(PostMortemEffects.BLEEDING.get()) ? _livEnt.getEffect(PostMortemEffects.BLEEDING.get()).getAmplifier() : 0) + 1) * 3), (entity.getBbWidth() / 2),
                    (entity.getBbHeight() / 4), (entity.getBbWidth() / 2), 0.01);
        if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(PostMortemEffects.BLEEDING.get()) ? _livEnt.getEffect(PostMortemEffects.BLEEDING.get()).getAmplifier() : 0) >= (world.getLevelData().getGameRules()
                .getInt(PostMortemGamerules.BLEEDING_ACTIVATION))) {
            if (world instanceof Level _level) {
                if (!_level.isClientSide()) {
                    _level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("post_mortem:bleeding_sound")), SoundSource.PLAYERS, 1, 1);
                } else {
                    _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("post_mortem:bleeding_sound")), SoundSource.PLAYERS, 1, 1, false);
                }
            }
            if (world instanceof ServerLevel _level)
                _level.sendParticles((SimpleParticleType) (ParticleTypes.DROP_OF_BLOOD.get()), x, (y + entity.getBbHeight() / 2), z,
                        (int) (((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(PostMortemEffects.BLEEDING.get()) ? _livEnt.getEffect(PostMortemEffects.BLEEDING.get()).getAmplifier() : 0) + 1) * 20),
                        (entity.getBbWidth() / 2), (entity.getBbHeight() / 2), (entity.getBbWidth() / 2), 1);
            if (entity instanceof LivingEntity _entity)
                _entity.removeEffect(PostMortemEffects.BLEEDING.get());
            if (!(entity instanceof Player)) {
                k = 1;
            } else {
                k = 2;
            }
            if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) - (world.getLevelData().getGameRules().getInt(PostMortemGamerules.BLEEDING_DAMAGE)) / k > 1) {
                if (entity instanceof LivingEntity _entity)
                    _entity.setHealth((float) ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) - (world.getLevelData().getGameRules().getInt(PostMortemGamerules.BLEEDING_DAMAGE)) / k));
            } else {
                if (entity instanceof LivingEntity _entity)
                    _entity.hurt(new DamageSource("bleeding").bypassArmor().bypassEnchantments().bypassInvul(), 80);
            }
        }
    } else {
        if (entity instanceof LivingEntity _entity)
            _entity.removeEffect(PostMortemEffects.BLEEDING.get());
        }
    }
}