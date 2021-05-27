package com.fischerworks.mobdoctoring.client.render;

import com.fischerworks.mobdoctoring.MobDoctoring;
import com.fischerworks.mobdoctoring.client.model.GhostZombieModel;
import com.fischerworks.mobdoctoring.entities.GhostZombieEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class GhostZombieRenderer extends MobRenderer<GhostZombieEntity, GhostZombieModel<GhostZombieEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(MobDoctoring.MOD_ID, "textures/entity/ghost_zombie.png");
    static final Function<ResourceLocation, RenderType> func = (ResourceLocation res) -> { return RenderType.getEntityTranslucent(res); };


    public GhostZombieRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GhostZombieModel<>(func, 0, 0, 64, 64), 0.7f); //adjust this
    }

    @Override
    public ResourceLocation getEntityTexture(GhostZombieEntity entity) {
        return TEXTURE;
    }
}
