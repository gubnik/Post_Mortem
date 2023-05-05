package net.team_prometheus.post_mortem.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PostMortemGameRules {
    public static final GameRules.Key<GameRules.BooleanValue> UNIVERSAL_BLEEDING = GameRules.register("universalBleeding", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));
    public static final GameRules.Key<GameRules.IntegerValue> BLEEDING_ACTIVATION = GameRules.register("bleedingActivation", GameRules.Category.PLAYER, GameRules.IntegerValue.create(4));
    public static final GameRules.Key<GameRules.IntegerValue> BLEEDING_DAMAGE = GameRules.register("bleedingDamage", GameRules.Category.PLAYER, GameRules.IntegerValue.create(10));
    public static final GameRules.Key<GameRules.BooleanValue> GHOSTS_SPAWN_AFTER_DEATH = GameRules.register("ghostsSpawnAfterDeath", GameRules.Category.SPAWNING, GameRules.BooleanValue.create(true));
}