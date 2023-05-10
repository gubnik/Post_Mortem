package net.team_prometheus.post_mortem.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.team_prometheus.post_mortem.echoes_weapons.EchoesTooltip;
import net.team_prometheus.post_mortem.init.PostMortemRarity;
import net.team_prometheus.post_mortem.init.PostMortemTabs;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class SacredSpear extends SwordItem {
    public SacredSpear() {
        super(new Tier() {
            @Override
            public int getUses() {
                return 1230;
            }
            @Override
            public float getSpeed() {
                return 7f;
            }
            @Override
            public float getAttackDamageBonus() {
                return 0f;
            }
            @Override
            public int getLevel() {
                return 2;
            }
            @Override
            public int getEnchantmentValue() {
                return 17;
            }
            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return null;
            }
        }, 5, -2.8f, new Item.Properties().tab(PostMortemTabs.SANGUIMANCY_TAB).rarity(PostMortemRarity.BloodRarity()));
    }
    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        if (Screen.hasControlDown()) {
            list.add(Component.translatable("desc.formless_ritual.line1").withStyle(ChatFormatting.RED));
            list.add(Component.translatable("desc.formless_ritual.line2").withStyle(ChatFormatting.RED));
            list.add(Component.translatable("desc.formless_ritual.line3").withStyle(ChatFormatting.RED));
        } else {
            list.add(Component.translatable("desc.press_control").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD));
        }
    }
}
