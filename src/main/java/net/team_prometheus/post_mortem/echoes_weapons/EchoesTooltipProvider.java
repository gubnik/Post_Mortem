package net.team_prometheus.post_mortem.echoes_weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Objects;

public class EchoesTooltipProvider extends SwordItem {
    public EchoesTooltipProvider(int dmg, float atkSpeed, int uses, float speed, float dmgBonus, int level, int enchantment, Ingredient repair, CreativeModeTab tab, Rarity rarity) {
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
            public @NotNull Ingredient getRepairIngredient() {
                return repair;
            }
        }, dmg, atkSpeed, new Item.Properties().tab(tab).rarity(rarity));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        ChatFormatting color = ChatFormatting.DARK_GRAY;
        double skill = itemstack.getOrCreateTag().getDouble("skill");
        if(skill % 2 != 0 && skill % 2 == 0) color = ChatFormatting.RED;
        else if(skill != 0 && skill % 2 == 1) color = ChatFormatting.AQUA;
        if (Screen.hasControlDown()) {
            if (Objects.equals(EchoesTooltip.getDescription(itemstack, list), "desc.no_skill")) {
                list.add(Component.translatable(EchoesTooltip.getDescription(itemstack, list)).withStyle(color));
            } else {
                list.add(Component.translatable(EchoesTooltip.getDescription(itemstack, list) + ".line1").withStyle(color));
                list.add(Component.translatable(EchoesTooltip.getDescription(itemstack, list) + ".line2").withStyle(color));
                list.add(Component.translatable(EchoesTooltip.getDescription(itemstack, list) + ".line3").withStyle(color));
            }
        } else {
            list.add(Component.translatable("desc.press_control").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.BOLD));
        }
    }
}
