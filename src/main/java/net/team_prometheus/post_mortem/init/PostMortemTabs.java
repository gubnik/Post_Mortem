package net.team_prometheus.post_mortem.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.team_prometheus.post_mortem.item.ModItems;

public class PostMortemTabs {
    public static CreativeModeTab TAB_PM_SOULBENDING_TAB;
    public static CreativeModeTab TAB_PM_SANGUIMANCY_TAB;

    public static void load() {
        TAB_PM_SOULBENDING_TAB = new CreativeModeTab("tabpm_soulbending_tab") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ModItems.SPIRIT_FRAGMENT.get());
            }

            @Override
            public boolean hasSearchBar() {
                return false;
            }
        };
        TAB_PM_SANGUIMANCY_TAB = new CreativeModeTab("tabpm_sanguimancy_tab") {
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

