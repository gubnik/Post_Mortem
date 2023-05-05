package net.team_prometheus.post_mortem.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.player.AbstractClientPlayer;
import java.util.Map;
import java.util.IdentityHashMap;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.AnimationStack;
import net.team_prometheus.post_mortem.Post_Mortem;

@Mod.EventBusSubscriber(modid = Post_Mortem.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupAnimations {
    public static final Map<AbstractClientPlayer, ModifierLayer<IAnimation>> animationData = new IdentityHashMap<>();

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        PlayerAnimationAccess.REGISTER_ANIMATION_EVENT.register(SetupAnimations::registerPlayerAnimation);
    }

    private static void registerPlayerAnimation(AbstractClientPlayer player, AnimationStack stack) {
        var layer = new ModifierLayer<>();
        stack.addAnimLayer(1000, layer); //Register the layer with a priority
        SetupAnimations.animationData.put(player, layer);
    }

}
