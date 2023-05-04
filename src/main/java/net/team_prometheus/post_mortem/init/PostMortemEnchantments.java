package net.team_prometheus.post_mortem.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.team_prometheus.post_mortem.Post_Mortem;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PostMortemEnchantments {
    public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Post_Mortem.MOD_ID);
    public static final RegistryObject<Enchantment> RUPTURE = REGISTRY.register("rupture", () -> new Enchantment(Enchantment.Rarity.COMMON, EnchantmentCategory.WEAPON, new EquipmentSlot[]{}) {
        public int getMaxLevel(){
            return 5;
        }
        @Override
        public void doPostAttack(@NotNull LivingEntity attacker, @NotNull Entity target, int level) {
            if(target instanceof LivingEntity _entity)
                if (Math.random() < level * 0.2) {
                    _entity.addEffect(new MobEffectInstance(PostMortemEffects.BLEEDING.get(), 100,
                            ((_entity.hasEffect(PostMortemEffects.BLEEDING.get()) ? Objects.requireNonNull(_entity.getEffect(PostMortemEffects.BLEEDING.get())).getAmplifier() : 0) + 1)));
            } // rupture
        }
    });
    public static final RegistryObject<Enchantment> EXORCISM = REGISTRY.register("exorcism", () -> new Enchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{}) {
        public int getMaxLevel(){
            return 3;
        }
        @Override
        public float getDamageBonus(int level, MobType mobType, ItemStack enchantedItem){
            if(mobType == PostMortemMobTypes.GHOST) {
                return level * 2f;
            } else return 0f;
        }
    });
}
