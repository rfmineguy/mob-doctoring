package com.fischerworks.mobdoctoring.client.render;

import com.fischerworks.mobdoctoring.MobDoctoring;
import com.fischerworks.mobdoctoring.client.model.GhostCreeperModel;
import com.fischerworks.mobdoctoring.client.model.GhostSpiderModel;
import com.fischerworks.mobdoctoring.entities.GhostCreeperEntity;
import com.fischerworks.mobdoctoring.entities.GhostSpiderEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class GhostSpiderRenderer extends MobRenderer<GhostSpiderEntity, GhostSpiderModel<GhostSpiderEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(MobDoctoring.MOD_ID, "textures/entity/ghost_spider.png");
    static final Function<ResourceLocation, RenderType> func = (ResourceLocation res) -> { return RenderType.getEntityTranslucent(res); };


    public GhostSpiderRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GhostSpiderModel<>(func), 0.7f); //adjust this
    }

    @Override
    public ResourceLocation getEntityTexture(GhostSpiderEntity entity) {
        return TEXTURE;
    }
}
