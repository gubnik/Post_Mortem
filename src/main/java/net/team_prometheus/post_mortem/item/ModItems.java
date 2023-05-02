package net.team_prometheus.post_mortem.item;

import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.team_prometheus.post_mortem.Post_Mortem;
import net.team_prometheus.post_mortem.init.PostMortemTabs;
import net.team_prometheus.post_mortem.item.echoes_weapons.EchoesTooltipProvider;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, Post_Mortem.MOD_ID);
    public static final RegistryObject<Item> TOMBSTONE_SHARD = ITEMS.register("tombstone_shard",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> SPIRIT_FRAGMENT = ITEMS.register("spirit_fragment",
            () -> new Item(new Item.Properties().tab(PostMortemTabs.SOULBENDING_TAB)));
    public static final RegistryObject<Item> UNBOUND_BLOOD = ITEMS.register("unbound_blood",
            () -> new Item(new Item.Properties().tab(PostMortemTabs.SANGUIMANCY_TAB)));
    public static final RegistryObject<Item> BLOODLETTER = ITEMS.register("bloodletter",
            () -> new EchoesTooltipProvider(5,666, -2.4f, 0f, 4, 17, Ingredient.of(ModItems.UNBOUND_BLOOD.get())));

    public static void register (IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
