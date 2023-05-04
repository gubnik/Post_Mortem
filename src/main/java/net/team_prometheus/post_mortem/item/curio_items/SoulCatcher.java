package net.team_prometheus.post_mortem.item.curio_items;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.team_prometheus.post_mortem.init.PostMortemRarity;
import net.team_prometheus.post_mortem.init.PostMortemTabs;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class SoulCatcher extends Item implements ICurioItem {
    public SoulCatcher() {
        super(new Item.Properties().tab(PostMortemTabs.SOULBENDING_TAB).stacksTo(1).durability(514).rarity(PostMortemRarity.SoulflameRarity()));
    }
    @Override
    public void inventoryTick(@NotNull ItemStack itemstack, @NotNull Level world, @NotNull Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        if ((itemstack).getDamageValue() < 513) {
            itemstack.getOrCreateTag().putDouble("CustomModelData", 1);
        } else {
            itemstack.getOrCreateTag().putDouble("CustomModelData", 0);
        }
        if ((itemstack).getDamageValue() == 0) {
            {
                if (itemstack.hurt(1, RandomSource.create(), null)) {
                    itemstack.shrink(1);
                    itemstack.setDamageValue(0);
                }
            }
        }

    }
}
