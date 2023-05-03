package net.team_prometheus.post_mortem.init;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.team_prometheus.post_mortem.Post_Mortem;
import net.team_prometheus.post_mortem.enchantment.Rupture;

public class PostMortemEnchantments {
    public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Post_Mortem.MOD_ID);
    public static final RegistryObject<Enchantment> RUPTURE = REGISTRY.register("rupture", () -> new Rupture());

}
