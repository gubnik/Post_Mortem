package net.team_prometheus.post_mortem.item.echoes_weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Objects;

public class EchoesTooltipProvider extends SwordItem {
    public EchoesTooltipProvider(int dmg, int uses, float speed, float dmgBonus, int level, int enchantment, Ingredient repair) {
        super(new Tier() {
            @Override
            public int getUses() {
                return uses;
            }

            @Override
            public float getSpeed() {
                return speed;
            }

            @Override
            public float getAttackDamageBonus() {
                return dmgBonus;
            }

            @Override
            public int getLevel() {
                return level;
            }

            @Override
            public int getEnchantmentValue() {
                return enchantment;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return repair;
            }
        }, dmg, speed, new Item.Properties().tab(null));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        if (Screen.hasControlDown()) {
            if (Objects.equals(EchoesTooltip.getDescription(itemstack, list), "desc.no_skill")) {
                list.add(Component.translatable(EchoesTooltip.getDescription(itemstack, list)));
            } else {
                list.add(Component.translatable(EchoesTooltip.getDescription(itemstack, list) + ".line1"));
                list.add(Component.translatable(EchoesTooltip.getDescription(itemstack, list) + ".line2"));
                list.add(Component.translatable(EchoesTooltip.getDescription(itemstack, list) + ".line3"));
            }
        } else {
            list.add(Component.translatable("desc.press_control").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD));
        }
    }
}
