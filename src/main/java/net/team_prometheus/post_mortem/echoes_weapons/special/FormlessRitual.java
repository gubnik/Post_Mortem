package net.team_prometheus.post_mortem.echoes_weapons.special;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.team_prometheus.post_mortem.Post_Mortem;
import net.team_prometheus.post_mortem.init.*;
import java.util.Comparator;
import java.util.List;

public class FormlessRitual {
    public static void execute(Player entity, ItemStack itemStack, LevelAccessor world){
        int delay = 0;
        for(int i = 0; i < 80; i++){
            delay+=1;
            Post_Mortem.queueServerWork(delay, () ->{
                entity.setShiftKeyDown(false);
                entity.setDeltaMovement(new Vec3(0, entity.getDeltaMovement().y(), 0));
            });
        }
        Post_Mortem.queueServerWork(10, ()->{
            effect(0.5, 5, entity.getX(), entity.getY(), entity.getZ(), entity, world);
            Post_Mortem.queueServerWork(21, ()->{
                effect(0.5, 5, entity.getX(), entity.getY(), entity.getZ(), entity, world);
                Post_Mortem.queueServerWork(21, ()->{
                    effect(0.5, 5, entity.getX(), entity.getY(), entity.getZ(), entity, world);
                });
            });
        });
    }
    public static void effect(double radius, float damage, double x, double y, double z, Entity entity, LevelAccessor world){
        if(world instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles((ParticleTypes.DROP_OF_BLOOD.get()), (entity.getX()), (entity.getY() + 3), (entity.getZ()), 50, radius, 0.1, radius, 0);
            Post_Mortem.queueServerWork(2, ()->{
                serverLevel.sendParticles((ParticleTypes.DROP_OF_BLOOD.get()), (entity.getX()), (entity.getY() + 3), (entity.getZ()), 50, radius*2, 0.1, radius*2, 0.1);
                Post_Mortem.queueServerWork(2, ()->{
                    serverLevel.sendParticles((ParticleTypes.DROP_OF_BLOOD.get()), (entity.getX()), (entity.getY() + 3), (entity.getZ()), 50, radius*4, 0.1, radius*4, 0.2);
                });
            });
            final Vec3 center = new Vec3(entity.getX(),entity.getY(),entity.getZ());
            List<Entity> entfound = world.getEntitiesOfClass(Entity.class, new AABB(center, center).inflate(8 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(center)))
                    .toList();
            for(Entity entIter : entfound){
                if(entIter instanceof LivingEntity liv && entIter != entity){
                    if(serverLevel.getGameRules().getBoolean(PostMortemGameRules.UNIVERSAL_BLEEDING) ||
                    !(liv.getMobType() == PostMortemMobTypes.GHOST || liv.getMobType() == MobType.UNDEAD || liv.getType().is(TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("forge:bleeding_immune"))))){
                        liv.hurt(PostMortemDamageSource.BLEED, damage);
                        liv.addEffect(new MobEffectInstance(PostMortemEffects.BLEEDING.get(), 100, (liv.hasEffect(PostMortemEffects.BLEEDING.get())) ? liv.getEffect(PostMortemEffects.BLEEDING.get()).getAmplifier() + 2: 0));
                    }
                }
            }
        }
    }
}