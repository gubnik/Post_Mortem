package net.team_prometheus.post_mortem.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.team_prometheus.post_mortem.init.PostMortemAttributes;
import net.team_prometheus.post_mortem.init.PostMortemDamageSource;
import net.team_prometheus.post_mortem.init.PostMortemMobTypes;
import java.util.Collection;
@Mod.EventBusSubscriber
public class MagicResistance {
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        if(event.getSource().isMagic() || event.getSource().getDirectEntity() instanceof LivingEntity living && living.getMobType() == PostMortemMobTypes.GHOST || event.getSource().equals(PostMortemDamageSource.SOULFLAME)){
            double reduction = 0;
            for (EquipmentSlot slot : EquipmentSlot.values()){
                ItemStack itemStack = entity.getItemBySlot(slot);
                Collection<AttributeModifier> modifiers = itemStack.getAttributeModifiers(slot).get(PostMortemAttributes.MAGIC_DAMAGE_REDUCTION.get());
                if(modifiers.isEmpty()) continue;
                reduction += modifiers.stream().mapToDouble(AttributeModifier::getAmount).sum();
            }
            if (reduction != 0)
                event.setAmount(event.getAmount() - (float) (event.getAmount() * reduction));
        }
    }
}