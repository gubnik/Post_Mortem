package net.team_prometheus.post_mortem.armor;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

// this code is heavily based on team abnormal savage&ravage blast protection

public enum MagicProofType implements StringRepresentable {
    HEAD("head", EquipmentSlot.HEAD, 15),
    CHEST("chest", EquipmentSlot.CHEST, 20),
    LEGS("legs", EquipmentSlot.LEGS, 15),
    FEET("feet", EquipmentSlot.FEET, 10);
    private final String name;
    private final EquipmentSlot slot;
    private final int reduction;
    MagicProofType(String name, EquipmentSlot slot, int reduction) {
        this.name = name;
        this.slot = slot;
        this.reduction = reduction;
    }
    public EquipmentSlot getSlot() {
        return this.slot;
    }
    public static MagicProofType slotToType(EquipmentSlot slot) {
        for (MagicProofType type : values())
            if (type.slot == slot)
                return type;
        return HEAD;
    }
    public float getReductionAmount() {
        return this.reduction * 0.01F;
    }
    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }
}