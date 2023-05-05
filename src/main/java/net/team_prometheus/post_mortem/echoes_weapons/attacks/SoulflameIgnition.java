package net.team_prometheus.post_mortem.echoes_weapons.attacks;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.team_prometheus.post_mortem.init.PostMortemDamageSource;
import java.util.List;
import java.util.Comparator;

public class SoulflameIgnition {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        double dx, dy, dz, k;
        for (int index0 = 0; index0 < (int) (3); index0++) {
            dx = 0;
            dy = 0;
            dz = 0;
            k = 0;
            for (int index1 = 0; index1 < 160; index1++) {
                if (world instanceof ServerLevel _level)
                    _level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, (x + entity.getLookAngle().x * k + dx), (y + entity.getLookAngle().y * k + dy), (z + entity.getLookAngle().z * k + dz), 1, 0, 0, 0, 0.02);
                {
                    final Vec3 _center = new Vec3((x + entity.getLookAngle().x * k + dx), (y + entity.getLookAngle().y * k + dy), (z + entity.getLookAngle().z * k + dz));
                    List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(1 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
                            .toList();
                    for (Entity entityiterator : _entfound) {
                        if (!(entity == entityiterator)) {
                            if (entityiterator instanceof LivingEntity _entity) {
                                _entity.hurt(PostMortemDamageSource.SOULFLAME, (_entity.getMaxHealth() / 20 + 6));
                            }
                        }
                    }
                }
                k = k + 0.05;
                if (Mth.nextInt(RandomSource.create(), 1, 5) <= 1 && k > 1) {
                    if (Mth.nextInt(RandomSource.create(), 1, 12) <= 1) {
                        dx = dx + Mth.nextDouble(RandomSource.create(), -0.5, 0.5);
                    } else {
                        if (Mth.nextInt(RandomSource.create(), 1, 2) <= 1) {
                            dy = dy + Mth.nextDouble(RandomSource.create(), -0.2, 0.2);
                        } else {
                            dz = dz + Mth.nextDouble(RandomSource.create(), -0.5, 0.5);
                        }
                    }
                }
            }
        }
    }
}
