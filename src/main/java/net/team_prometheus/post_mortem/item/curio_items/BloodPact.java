package net.team_prometheus.post_mortem.item.curio_items;

import top.theillusivec4.curios.api.type.capability.ICurioItem;
import net.team_prometheus.post_mortem.init.PostMortemTabs;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class BloodPact extends Item implements ICurioItem {
    public BloodPact() {
        super(new Item.Properties().tab(PostMortemTabs.SANGUIMANCY_TAB).stacksTo(1).rarity(Rarity.EPIC));
    }
}
