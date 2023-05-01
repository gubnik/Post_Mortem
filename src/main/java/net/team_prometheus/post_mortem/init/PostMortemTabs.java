package net.team_prometheus.post_mortem.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.team_prometheus.post_mortem.item.ModItems;

public class PostMortemTabs {
    public static final CreativeModeTab SOULBENDING_TAB = new CreativeModeTab("soulbending_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SPIRIT_FRAGMENT.get());
        }
    };
    public static final CreativeModeTab SANGUIMANCY_TAB = new CreativeModeTab("sanguimancy_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.UNBOUND_BLOOD.get());
        }
    };
}


