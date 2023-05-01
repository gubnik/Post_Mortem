package net.team_prometheus.post_mortem.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.team_prometheus.post_mortem.item.ModItems;

public class PostMortemTabs {
    public static CreativeModeTab SOULBENDING_TAB;
    public static CreativeModeTab SANGUIMANCY_TAB;

    public static void load() {
        SOULBENDING_TAB = new CreativeModeTab("soulbending_tab") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ModItems.SPIRIT_FRAGMENT.get());
            }

            @Override
            public boolean hasSearchBar() {
                return false;
            }
        };
        SANGUIMANCY_TAB = new CreativeModeTab("sanguimancy_tab") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ModItems.UNBOUND_BLOOD.get());
            }

            @Override
            public boolean hasSearchBar() {
                return false;
            }
        };
    }
}

