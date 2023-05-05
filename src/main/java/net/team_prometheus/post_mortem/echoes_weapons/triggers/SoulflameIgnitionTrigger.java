package net.team_prometheus.post_mortem.echoes_weapons.triggers;

import net.minecraft.world.item.Item;
import net.team_prometheus.post_mortem.echoes_weapons.attacks.SoulflameIgnition;
import net.team_prometheus.post_mortem.init.SetupAnimations;
import top.theillusivec4.curios.api.CuriosApi;

import net.team_prometheus.post_mortem.item.ModItems;
import net.team_prometheus.post_mortem.Post_Mortem;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.resources.ResourceLocation;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.AnimUtils;

import java.util.Objects;

public class SoulflameIgnitionTrigger {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack used_item) {
        if (entity == null)
            return;
        if (entity instanceof Player player)
            if(CuriosApi.getCuriosHelper().findFirstCurio(player, ModItems.SOUL_CATCHER.get()).isPresent()) {
                ItemStack item = CuriosApi.getCuriosHelper().findFirstCurio(player, ModItems.SOUL_CATCHER.get()).get().stack();
                if ((item).getDamageValue() <= ((item).getMaxDamage() - 8) - 1) {
                    player.getCooldowns().addCooldown(used_item.getItem(), 100);
                    var animation = SetupAnimations.animationData.get(player);
                    if (animation != null) {
                        animation.setAnimation(new KeyframeAnimationPlayer(Objects.requireNonNull(PlayerAnimationRegistry.getAnimation(new ResourceLocation("post_mortem", "soulflame_ignition")))));
                    }
                    AnimUtils.disableFirstPersonAnim = false; {
                        if (item.hurt(8, RandomSource.create(), null)) {
                            item.shrink(1);
                            item.setDamageValue(0);
                        }
                    }
                    Post_Mortem.queueServerWork(5, () -> SoulflameIgnition.execute(world, x, (y + 1.3), z, entity));
                }
            }
        }
    }
