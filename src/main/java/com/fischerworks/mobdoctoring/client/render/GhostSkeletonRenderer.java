package com.fischerworks.mobdoctoring.client.render;

import com.fischerworks.mobdoctoring.MobDoctoring;
import com.fischerworks.mobdoctoring.client.model.GhostCreeperModel;
import com.fischerworks.mobdoctoring.client.model.GhostSkeletonModel;
import com.fischerworks.mobdoctoring.entities.GhostCreeperEntity;
import com.fischerworks.mobdoctoring.entities.GhostSkeletonEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class GhostSkeletonRenderer extends MobRenderer<GhostSkeletonEntity, GhostSkeletonModel<GhostSkeletonEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(MobDoctoring.MOD_ID, "textures/entity/ghost_skeleton.png");
    static final Function<ResourceLocation, RenderType> func = (ResourceLocation res) -> { return RenderType.getEntityTranslucent(res); };

    public GhostSkeletonRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GhostSkeletonModel<>(func, 0, 0, 64, 32), 0.7f); //adjust this
    }

    @Override
    public ResourceLocation getEntityTexture(GhostSkeletonEntity entity) {
        return TEXTURE;
    }
}
