package net.team_prometheus.post_mortem.item;

import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.team_prometheus.post_mortem.Post_Mortem;
import net.team_prometheus.post_mortem.init.ModEntities;
import net.team_prometheus.post_mortem.init.PostMortemRarity;
import net.team_prometheus.post_mortem.init.PostMortemTabs;
import net.team_prometheus.post_mortem.item.curio_items.BloodPact;
import net.team_prometheus.post_mortem.item.curio_items.SoulCatcher;
import net.team_prometheus.post_mortem.echoes_weapons.EchoesTooltipProvider;

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

    // weapons, echo variants
    public static final RegistryObject<Item> BLOODLETTER = ITEMS.register("bloodletter",
            () -> new EchoesTooltipProvider(5, -2.4f, 1666, 14, 0f, 3, 14, Ingredient.of(ModItems.UNBOUND_BLOOD.get()), PostMortemTabs.SANGUIMANCY_TAB));
    public static final RegistryObject<Item> SOULFLAME_SPEAR = ITEMS.register("soulflame_spear",
            () -> new EchoesTooltipProvider(6, -2.6f, 1777, 14, 0f, 3, 17, Ingredient.of(ModItems.SPIRIT_FRAGMENT.get()), PostMortemTabs.SOULBENDING_TAB));
    public static final RegistryObject<Item> BINDING_BLADE = ITEMS.register("binding_blade",
            () -> new EchoesTooltipProvider(4, -2.4f, 666, 14, 0f, 2, 12, Ingredient.of(ModItems.TOMBSTONE_SHARD.get()), PostMortemTabs.SOULBENDING_TAB));

    // curios items
    public static final RegistryObject<Item> BLOOD_PACT = ITEMS.register("blood_pact",
            BloodPact::new);
    public static final RegistryObject<Item> SOUL_CATCHER = ITEMS.register("soul_catcher",
            SoulCatcher::new);

    // spawn eggs
    public static final RegistryObject<Item> ANGRY_SPIRIT_SPAWN_EGG = ITEMS.register("angry_spirit_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ANGRY_SPIRIT, -7233363, -3374717, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static void register (IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
