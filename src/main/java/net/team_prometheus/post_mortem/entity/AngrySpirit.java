package net.team_prometheus.post_mortem.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.team_prometheus.post_mortem.init.ModEntities;
import net.team_prometheus.post_mortem.init.PostMortemDamageSource;
import net.team_prometheus.post_mortem.init.PostMortemGameRules;
import net.team_prometheus.post_mortem.init.PostMortemMobTypes;
import org.jetbrains.annotations.NotNull;

public class AngrySpirit extends Monster implements Enemy {
    public AngrySpirit(EntityType<? extends AngrySpirit> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 2;
    }
    public static void init() {
        SpawnPlacements.register(ModEntities.ANGRY_SPIRIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
    }
    @Override
    public void registerGoals(){
        this.addBehaviourGoals();
    }
    @Override
    public void baseTick() {
        super.baseTick();
        GhostsDespawn.execute(this.level, this);
    }
    @Override
    public @NotNull MobType getMobType() {
        return PostMortemMobTypes.GHOST;
    }
    @Override
    public boolean hurt(@NotNull DamageSource dmg, float amount){
        if(dmg == PostMortemDamageSource.SOULFLAME || dmg.getDirectEntity() instanceof LivingEntity attacker && attacker.getMainHandItem().is(ItemTags.create(new ResourceLocation("forge:ghosts_binders")))){
            super.hurt(dmg.bypassArmor(), amount*2);
            return true;
        } else if((dmg == PostMortemDamageSource.BLEED && !level.getGameRules().getBoolean(PostMortemGameRules.UNIVERSAL_BLEEDING)) || dmg == DamageSource.LIGHTNING_BOLT ||
        dmg == DamageSource.IN_FIRE || dmg == DamageSource.ON_FIRE || dmg == DamageSource.FREEZE) return false;
        else return super.hurt(dmg, amount);
    }
    public void addBehaviourGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(7, (new HurtByTargetGoal(this)).setAlertOthers(Villager.class));
    }
    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.2f);
        builder = builder.add(Attributes.MAX_HEALTH, 30);
        builder = builder.add(Attributes.ARMOR, 20);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 6);
        builder = builder.add(Attributes.FOLLOW_RANGE, 16);
        return builder;
    }
}
