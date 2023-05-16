package net.team_prometheus.post_mortem.echoes_weapons;

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
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class EchoesTooltipProvider extends SwordItem {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    public EchoesTooltipProvider(Tier tier, int dmg, float atkSpeed, CreativeModeTab tab, Rarity rarity, double bleed_application) {
        super(tier, dmg, atkSpeed, new Item.Properties().tab(tab).rarity(rarity));
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getDefaultAttributeModifiers(EquipmentSlot.MAINHAND));
        if(bleed_application != 0){
            builder.put(PostMortemAttributes.BLEED_APPLICATION.get(), new AttributeModifier(Post_Mortem.BLEED_APPLICATION, "Bleed application", bleed_application, AttributeModifier.Operation.ADDITION));
        }
        this.defaultModifiers = builder.build();
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
    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }
}
