package net.team_prometheus.post_mortem.echoes_weapons;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.team_prometheus.post_mortem.echoes_weapons.triggers.SoulflameIgnitionTrigger;

@Mod.EventBusSubscriber
public class EchoesMainTrigger {
    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event){
        execute(event.getItemStack(), event.getEntity(), event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
    }
    public static void execute(ItemStack item, Entity entity, LevelAccessor world, double x, double y, double z){
        if(entity == null){return;}
        if(entity instanceof LivingEntity user && item.is(ItemTags.create(new ResourceLocation("forge:sanguine_echoes_empty_weapons"))))
            switch((int) item.getOrCreateTag().getDouble("skill")){
                case(0):
                    item.getOrCreateTag().putDouble("skill", 1); // this will be deleted when I'm done with testing
                case(1):
                    SoulflameIgnitionTrigger.execute(world, x, y, z, user, item);
                    break;
                case(2):
                    break;
            }
    }
}
