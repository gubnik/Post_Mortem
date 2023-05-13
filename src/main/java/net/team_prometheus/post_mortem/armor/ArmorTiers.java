package net.team_prometheus.post_mortem.armor;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;
import net.team_prometheus.post_mortem.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ArmorTiers {
    public static final ArmorMaterial SOULFILLED = new ArmorMaterial() {
        @Override
        public int getDurabilityForSlot(EquipmentSlot slot) {
            return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
        }
        @Override
        public int getDefenseForSlot(EquipmentSlot slot) {
            return new int[]{2, 4, 6, 2}[slot.getIndex()];
        }
        @Override
        public int getEnchantmentValue() {
            return 17;
        }
        @Override
        public @NotNull SoundEvent getEquipSound() {
            return Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")));
        }
        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(ModItems.SOULFILLED_INGOT.get());
        }
        @Override
        public String getName() {
            return "soulfilled";
        }
        @Override
        public float getToughness() {
            return 0.5f;
        }
        @Override
        public float getKnockbackResistance() {
            return 0;
        }
    };
};

