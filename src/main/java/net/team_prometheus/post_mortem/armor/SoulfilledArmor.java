package net.team_prometheus.post_mortem.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.team_prometheus.post_mortem.init.PostMortemAttributes;
import org.jetbrains.annotations.NotNull;
public abstract class SoulfilledArmor extends ArmorItem {
    private final LazyLoadedValue<Multimap<Attribute, AttributeModifier>> attributes;
    public SoulfilledArmor(ArmorMaterial material, EquipmentSlot slot, Item.Properties properties) {
        super(material, slot, properties);
        this.attributes = new LazyLoadedValue<>(() -> {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.getDefaultAttributeModifiers(slot));
            builder.put(PostMortemAttributes.MAGIC_DAMAGE_REDUCTION.get(), new AttributeModifier("Magic proof", MagicProofType.slotToType(slot).getReductionAmount(), AttributeModifier.Operation.ADDITION));
            return builder.build();
        });
    }
    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot equipmentSlot) {
        return equipmentSlot == this.slot ? this.attributes.get() : super.getDefaultAttributeModifiers(equipmentSlot);
    }
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if(slot == EquipmentSlot.LEGS)return "post_mortem:textures/models/armor/soulfilled_layer_2.png";
        else return "post_mortem:textures/models/armor/soulfilled_layer_1.png";
    }
}

