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
    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            list.add(Component.translatable("desc.blood_pact.line1"));
        } else {
            list.add(Component.translatable("desc.press_shift").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
        }
        super.appendHoverText(itemstack, world, list, flag);
    }
}
