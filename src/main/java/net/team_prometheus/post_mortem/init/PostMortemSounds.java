package net.team_prometheus.post_mortem.init;

import net.team_prometheus.post_mortem.Post_Mortem;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

public class PostMortemSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Post_Mortem.MOD_ID);
    public static final RegistryObject<SoundEvent> BLEEDING_SOUND = REGISTRY.register("bleeding_sound", () -> new SoundEvent(new ResourceLocation("post_mortem", "bleeding_sound")));
    public static final RegistryObject<SoundEvent> CURSED_FOLIO_PAGE_SOUND = REGISTRY.register("cursed_folio_page_sound", () -> new SoundEvent(new ResourceLocation("post_mortem", "cursed_folio_page_sound")));
}
