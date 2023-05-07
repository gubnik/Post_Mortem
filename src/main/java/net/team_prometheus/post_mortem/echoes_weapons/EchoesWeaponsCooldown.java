package net.team_prometheus.post_mortem.echoes_weapons;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import java.util.concurrent.atomic.AtomicReference;

public class EchoesWeaponsCooldown {
    public static void echoWeaponsCooldown(Player player, int time, ItemStack used_item){
        AtomicReference<IItemHandler> itemHandReference = new AtomicReference<>();
        player.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(itemHandReference::set);
        if (itemHandReference.get() != null) {
            for (int _idx = 0; _idx < itemHandReference.get().getSlots(); _idx++) {
                ItemStack itemStack = itemHandReference.get().getStackInSlot(_idx).copy();
                if (itemStack.getOrCreateTag().getDouble("skill") == used_item.getOrCreateTag().getDouble("skill")) {
                    player.getCooldowns().addCooldown(itemStack.getItem(), time);
                }
            }
        }
    }
}
