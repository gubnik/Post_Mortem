package net.team_prometheus.post_mortem.item.curio_items;

import top.theillusivec4.curios.api.type.capability.ICurioItem;

import net.team_prometheus.post_mortem.init.PostMortemTabs;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;

import java.util.List;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.ChatFormatting;

public class BloodPact extends Item implements ICurioItem {
    public BloodPact() {
        super(new Item.Properties().tab(PostMortemTabs.SANGUIMANCY_TAB).stacksTo(1).rarity(Rarity.EPIC));
    }
}
