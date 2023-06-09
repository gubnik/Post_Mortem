package net.team_prometheus.post_mortem.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.team_prometheus.post_mortem.Post_Mortem;
import net.team_prometheus.post_mortem.armor.ArmorTiers;
import net.team_prometheus.post_mortem.armor.SoulfilledArmor;
import net.team_prometheus.post_mortem.init.ModEntities;
import net.team_prometheus.post_mortem.init.PostMortemRarity;
import net.team_prometheus.post_mortem.init.PostMortemTabs;
import net.team_prometheus.post_mortem.item.curio_items.BloodPact;
import net.team_prometheus.post_mortem.item.curio_items.SoulCatcher;
import net.team_prometheus.post_mortem.echoes_weapons.EchoesTooltipProvider;
import org.jetbrains.annotations.NotNull;

public class ModItems {

    // materials
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, Post_Mortem.MOD_ID);
    public static final RegistryObject<Item> TOMBSTONE_SHARD = ITEMS.register("tombstone_shard",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> HAUNTED_SPLINTER = ITEMS.register("haunted_splinter",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> SPIRIT_FRAGMENT = ITEMS.register("spirit_fragment",
            () -> new Item(new Item.Properties().tab(PostMortemTabs.SOULBENDING_TAB).rarity(PostMortemRarity.SoulflameRarity())));
    public static final RegistryObject<Item> SOULFILLED_INGOT = ITEMS.register("soulfilled_ingot",
            () -> new Item(new Item.Properties().tab(PostMortemTabs.SOULBENDING_TAB).rarity(PostMortemRarity.SoulflameRarity())));
    public static final RegistryObject<Item> UNBOUND_BLOOD = ITEMS.register("unbound_blood",
            () -> new Item(new Item.Properties().tab(PostMortemTabs.SANGUIMANCY_TAB).rarity(PostMortemRarity.BloodRarity())));
    public static final RegistryObject<Item> BLOODSTAINED_INGOT = ITEMS.register("bloodstained_ingot",
            () -> new Item(new Item.Properties().tab(PostMortemTabs.SANGUIMANCY_TAB).rarity(PostMortemRarity.BloodRarity())));
    public static final RegistryObject<Item> VIAL = ITEMS.register("vial",
            () -> new Item(new Item.Properties().tab(PostMortemTabs.SANGUIMANCY_TAB)));
    public static final RegistryObject<Item> BLOOD_VIAL = ITEMS.register("blood_vial",
            () -> new Item(new Item.Properties().tab(PostMortemTabs.SANGUIMANCY_TAB).rarity(PostMortemRarity.BloodRarity()).
                    food(new FoodProperties.Builder().nutrition(-1).saturationMod(0f).alwaysEat().build())){
                @Override
                public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemstack) {
                    return UseAnim.CROSSBOW;
                }
                @Override
                public int getUseDuration(@NotNull ItemStack itemstack) {
                    return 20;
                }
            });

    // weapons
    public static final RegistryObject<Item> BLOODLETTER = ITEMS.register("bloodletter",
            () -> new EchoesTooltipProvider(WeaponTiers.BLOOD, 5, -2.4f, PostMortemTabs.SANGUIMANCY_TAB, PostMortemRarity.BloodRarity(), 0.5));
    public static final RegistryObject<Item> SOULFLAME_SPEAR = ITEMS.register("soulflame_spear",
            () -> new EchoesTooltipProvider(WeaponTiers.SOULFLAME, 6, -2.4f, PostMortemTabs.SOULBENDING_TAB, PostMortemRarity.SoulflameRarity(), 0));
    public static final RegistryObject<Item> BINDING_BLADE = ITEMS.register("binding_blade",
            () -> new EchoesTooltipProvider(WeaponTiers.SOULFLAME, 3, -2.4f, PostMortemTabs.SOULBENDING_TAB, PostMortemRarity.SoulflameRarity(), 0));
    public static final RegistryObject<Item> SACRED_SPEAR = ITEMS.register("sacred_spear",
            SacredSpear::new);

    // curios items
    public static final RegistryObject<Item> BLOOD_PACT = ITEMS.register("blood_pact",
            BloodPact::new);
    public static final RegistryObject<Item> SOUL_CATCHER = ITEMS.register("soul_catcher",
            SoulCatcher::new);

    // spawn eggs
    public static final RegistryObject<Item> ANGRY_SPIRIT_SPAWN_EGG = ITEMS.register("angry_spirit_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ANGRY_SPIRIT, -7233363, -3374717, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    // armor
    public static final RegistryObject<Item> SOULFILLED_HELMET = ITEMS.register("soulfilled_helmet", () -> new SoulfilledArmor(ArmorTiers.SOULFILLED, EquipmentSlot.HEAD, new Item.Properties().tab(PostMortemTabs.SOULBENDING_TAB).rarity(PostMortemRarity.SoulflameRarity())) {
    });
    public static final RegistryObject<Item> SOULFILLED_CHESTPLATE = ITEMS.register("soulfilled_chestplate", () -> new SoulfilledArmor(ArmorTiers.SOULFILLED, EquipmentSlot.CHEST, new Item.Properties().tab(PostMortemTabs.SOULBENDING_TAB).rarity(PostMortemRarity.SoulflameRarity())) {
    });
    public static final RegistryObject<Item> SOULFILLED_LEGGINGS = ITEMS.register("soulfilled_leggings", () -> new SoulfilledArmor(ArmorTiers.SOULFILLED, EquipmentSlot.LEGS, new Item.Properties().tab(PostMortemTabs.SOULBENDING_TAB).rarity(PostMortemRarity.SoulflameRarity())) {
    });
    public static final RegistryObject<Item> SOULFILLED_BOOTS = ITEMS.register("soulfilled_boots", () -> new SoulfilledArmor(ArmorTiers.SOULFILLED, EquipmentSlot.FEET, new Item.Properties().tab(PostMortemTabs.SOULBENDING_TAB).rarity(PostMortemRarity.SoulflameRarity())) {
    });

    public static void register (IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
