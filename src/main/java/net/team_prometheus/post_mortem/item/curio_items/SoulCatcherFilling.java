package net.team_prometheus.post_mortem.item.curio_items;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.team_prometheus.post_mortem.item.ModItems;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber
public class SoulCatcherFilling {
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event){
        if(event != null && event.getEntity() != null){
            execute(event, event.getEntity().level, event.getEntity(), event.getSource().getEntity());
        }
    }
    public static void execute(Event event, Level world, Entity entity, Entity source){
        if(entity == null || source == null){
            return;
        }
        if(entity instanceof LivingEntity target && source instanceof LivingEntity cause)
            if(cause.getMainHandItem().is(ItemTags.create(new ResourceLocation("forge:ghosts_binders"))) &&
            CuriosApi.getCuriosHelper().findFirstCurio(cause, ModItems.SOUL_CATCHER.get()).isPresent() &&
            target.getType().is(TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("forge:ghosts")))) {
                ItemStack item = (ItemStack) CuriosApi.getCuriosHelper().findFirstCurio(cause, ModItems.SOUL_CATCHER.get()).get().stack();
                if ((item).getDamageValue() - 16 > 1) {
                    (item).setDamageValue((int) ((item).getDamageValue() - 16));
                } else if ((item).getDamageValue() - 16 <= 1) {
                    (item).setDamageValue(1);
                }
            }
    }
}