package net.team_prometheus.post_mortem.potion;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;
import net.team_prometheus.post_mortem.init.*;
import net.minecraft.world.entity.Entity;
import java.util.Objects;
public class BleedingEffect {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
    float k;
    if(world instanceof ServerLevel _level && entity instanceof LivingEntity _entity)
        if (!(_entity.getMobType() == MobType.UNDEAD) &&  !(_entity.getMobType() == PostMortemMobTypes.GHOST) && !world.getLevelData().getGameRules().getBoolean(PostMortemGamerules.UNIVERSAL_BLEEDING)
        && !entity.getType().is(TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("forge:bleeding_immune"))) || world.getLevelData().getGameRules().getBoolean(PostMortemGamerules.UNIVERSAL_BLEEDING)) {
        _level.sendParticles((ParticleTypes.DROP_OF_BLOOD.get()), x, (y + entity.getBbHeight() / 2), z,
                    (((_entity.hasEffect(PostMortemEffects.BLEEDING.get()) ? _entity.getEffect(PostMortemEffects.BLEEDING.get()).getAmplifier() : 0) + 1) * 3), (entity.getBbWidth() / 2),
                    (entity.getBbHeight() / 4), (entity.getBbWidth() / 2), 0.01);
        if ((_entity.hasEffect(PostMortemEffects.BLEEDING.get()) ? _entity.getEffect(PostMortemEffects.BLEEDING.get()).getAmplifier() : 0) >= (world.getLevelData().getGameRules()
                .getInt(PostMortemGamerules.BLEEDING_ACTIVATION))) {
            if (!_level.isClientSide()) {
                _level.playSound(null, new BlockPos(x, y, z), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("post_mortem:bleeding_sound"))), SoundSource.PLAYERS, 1, 1);
            } else {
                _level.playLocalSound(x, y, z, Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("post_mortem:bleeding_sound"))), SoundSource.PLAYERS, 1, 1, false);
            }
            _level.sendParticles((ParticleTypes.DROP_OF_BLOOD.get()), x, (y + entity.getBbHeight() / 2), z,
                    (((_entity.hasEffect(PostMortemEffects.BLEEDING.get()) ? _entity.getEffect(PostMortemEffects.BLEEDING.get()).getAmplifier() : 0) + 1) * 20),
                    (entity.getBbWidth() / 2), (entity.getBbHeight() / 2), (entity.getBbWidth() / 2), 1);
            _entity.removeEffect(PostMortemEffects.BLEEDING.get());
            k = 1 + ((entity instanceof Player) ? 1: 0);
            _entity.hurt(PostMortemDamageSource.BLEED, (world.getLevelData().getGameRules().getInt(PostMortemGamerules.BLEEDING_DAMAGE)) / k);
        }
    } else {
        _entity.removeEffect(PostMortemEffects.BLEEDING.get());
        }
    }
}