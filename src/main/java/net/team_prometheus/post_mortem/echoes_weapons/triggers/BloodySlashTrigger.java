package net.team_prometheus.post_mortem.echoes_weapons.triggers;

import dev.kosmx.playerAnim.api.AnimUtils;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.team_prometheus.post_mortem.Post_Mortem;
import net.team_prometheus.post_mortem.echoes_weapons.EchoesWeaponsCooldown;
import net.team_prometheus.post_mortem.echoes_weapons.attacks.BloodySlash;
import net.team_prometheus.post_mortem.init.SetupAnimations;

import java.util.Objects;

public class BloodySlashTrigger {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack used_item) {
        if (entity == null)
            return;
        if(entity instanceof Player player)
            if (player.getHealth() - 2 > 0 || (player.getAbilities().instabuild)) {
                if (!(player.getAbilities().instabuild)) {
                    player.setHealth(((player.getHealth()) - 2));
                }
                EchoesWeaponsCooldown.echoWeaponsCooldown(player, 100, used_item);
                var animation = SetupAnimations.animationData.get(player);
                if (animation != null) {
                    animation.setAnimation(new KeyframeAnimationPlayer(Objects.requireNonNull(PlayerAnimationRegistry.getAnimation(new ResourceLocation("post_mortem", "bloody_slash")))));
                }
                AnimUtils.disableFirstPersonAnim = false;
                Post_Mortem.queueServerWork(4, () -> BloodySlash.execute(world, player.getX(), player.getY(), player.getZ(), entity));
        }
    }
}