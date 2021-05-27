package com.fischerworks.mobdoctoring.client.model;

import com.fischerworks.mobdoctoring.entities.GhostZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.AbstractZombieModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class GhostZombieModel<G extends MonsterEntity> extends BipedModel<GhostZombieEntity> {

    public GhostZombieModel(Function<ResourceLocation, RenderType> renderTypeIn, float modelSizeIn, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
        super(renderTypeIn, modelSizeIn, yOffsetIn, textureWidthIn, textureHeightIn);
    }

    @Override
    public void setRotationAngles(GhostZombieEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        super.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
