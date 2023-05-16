package net.team_prometheus.post_mortem.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.team_prometheus.post_mortem.Post_Mortem;
import net.team_prometheus.post_mortem.init.PostMortemAttributes;
import net.team_prometheus.post_mortem.init.PostMortemRarity;
import net.team_prometheus.post_mortem.init.PostMortemTabs;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.UUID;

public class SacredSpear extends SwordItem {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    public SacredSpear() {
        super(WeaponTiers.BLOOD, 5, -2.8f, new Item.Properties().tab(PostMortemTabs.SANGUIMANCY_TAB).rarity(PostMortemRarity.BloodRarity()));
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getDefaultAttributeModifiers(EquipmentSlot.MAINHAND));
        builder.put(PostMortemAttributes.BLEED_APPLICATION.get(), new AttributeModifier(Post_Mortem.BLEED_APPLICATION, "Bleed application", 0.6, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
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
    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }
}
