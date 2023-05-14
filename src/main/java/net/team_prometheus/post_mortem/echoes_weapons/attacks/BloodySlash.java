package net.team_prometheus.post_mortem.echoes_weapons.attacks;

import net.team_prometheus.post_mortem.init.ParticleTypes;
import net.team_prometheus.post_mortem.init.PostMortemDamageSource;
import net.team_prometheus.post_mortem.init.PostMortemEffects;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerLevel;
import java.util.Objects;
import java.util.List;
import java.util.Comparator;

public class BloodySlash {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) return;
        double pitch, yaw;
        pitch = entity.getYRot() - 60;
        yaw = entity.getXRot() - 60;
        for (int index0 = 0; index0 < 120; index0++) {
            if (world instanceof ServerLevel _level)
                _level.sendParticles((ParticleTypes.DROP_OF_BLOOD.get()), (entity.getX() - Math.sin(Math.toRadians(yaw)) * 2), ((entity.getY() + 1.5) - Math.sin(Math.toRadians(pitch)) * 2),
                        (entity.getZ() + Math.cos(Math.toRadians(yaw)) * 2), 2, 0.1, 0.1, 0.1, 0.3);
            yaw += 1;
            pitch += 1;
        }
        final Vec3 center = new Vec3((x + entity.getLookAngle().x * 2), (y + entity.getLookAngle().y * 2 + 1.4), (z + entity.getLookAngle().z * 2));
        List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(center, center).inflate(4 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(center))).toList();
        for (Entity entityiterator : _entfound) {
            if (!(entityiterator == entity) && entityiterator instanceof LivingEntity _entity) {
                _entity.hurt(PostMortemDamageSource.BLEED, 4);
                _entity.addEffect(new MobEffectInstance(PostMortemEffects.BLEEDING.get(), 80,
                        (_entity.hasEffect(PostMortemEffects.BLEEDING.get()) ? Objects.requireNonNull(_entity.getEffect(PostMortemEffects.BLEEDING.get())).getAmplifier() + 1 : 0), (false),
                        (true)));
            }
        }
    }
}
