package net.team_prometheus.post_mortem.echoes_weapons.special;

import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.team_prometheus.post_mortem.init.SetupAnimations;
import net.team_prometheus.post_mortem.item.ModItems;

import java.util.Objects;

@Mod.EventBusSubscriber
public class FormlessRitualTrigger {
    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event){
        if(event.getEntity() != null && event.getItemStack().getItem() == ModItems.SACRED_SPEAR.get()){
            ItemStack itemStack = event.getItemStack();
            LevelAccessor world = event.getLevel();
            Player entity = event.getEntity();
            if(entity.getAbilities().instabuild || entity.getHealth() > 5) {
                entity.getCooldowns().addCooldown(itemStack.getItem(), 200);
                var animation = SetupAnimations.animationData.get(entity);
                if (animation != null) {
                    animation.setAnimation(new KeyframeAnimationPlayer(Objects.requireNonNull(PlayerAnimationRegistry.getAnimation(new ResourceLocation("post_mortem", "bloodboon_ritual")))));
                }
                if(!entity.getAbilities().instabuild)entity.setHealth(entity.getHealth() - 5);
                if (itemStack.hurt(32, RandomSource.create(), null)) {
                    itemStack.shrink(1);
                    itemStack.setDamageValue(0);
                }
                FormlessRitual.execute(entity, itemStack, world);
            }
        }
    }
}
