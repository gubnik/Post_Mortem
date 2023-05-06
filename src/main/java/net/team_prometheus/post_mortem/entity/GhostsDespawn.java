package net.team_prometheus.post_mortem.entity;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.team_prometheus.post_mortem.Post_Mortem;

public class GhostsDespawn {
    public static void execute(LevelAccessor world, Entity entity) {
        if (entity == null)
            return;
        if (entity.getPersistentData().getDouble("ghost_power") != 0) {
            Post_Mortem.queueServerWork((int) entity.getPersistentData().getDouble("ghost_power"), () -> {
                if (!entity.level.isClientSide())
                    entity.discard();
            });
        }
    }
}
