package net.team_prometheus.post_mortem.renderer;


import net.team_prometheus.post_mortem.entity.AngrySpirit;
import net.team_prometheus.post_mortem.models.AngrySpiritModel;
import org.jetbrains.annotations.NotNull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;

public class AngrySpiritRenderer extends MobRenderer<AngrySpirit, AngrySpiritModel<AngrySpirit>> {
    public AngrySpiritRenderer(EntityRendererProvider.Context context) {
        super(context, new AngrySpiritModel<>(context.bakeLayer(AngrySpiritModel.LAYER_LOCATION)), 0.5f);
        this.addLayer(new EyesLayer<AngrySpirit, AngrySpiritModel<AngrySpirit>>(this) {
            @Override
            public @NotNull RenderType renderType() {
                return RenderType.eyes(new ResourceLocation("post_mortem:textures/entities/angry_spirit.png"));
            }
        });
    }
    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull AngrySpirit entity) {
        return new ResourceLocation("post_mortem:textures/entities/angry_spirit.png");
    }
}

