package net.team_prometheus.post_mortem.item.echoes_weapons;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;


import java.util.List;

public class EchoesTooltip {
    public static String getDescription(ItemStack itemstack, List<Component> list) {
        String returned = switch ((int) itemstack.getOrCreateTag().getDouble("skill")) {
            case (0) -> "desc.no_skill";
            case (1) -> "desc.soulflame_ignition";
            case (2) -> "desc.bloody_slash";
            case (3) -> "desc.ghostly_form";
            case (4) -> "desc.seppuku";
            case (5) -> "desc.spirit_call";
            case (6) -> "desc.revenge";
            default -> "default";
        };
        return returned;
    }
}