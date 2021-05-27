package com.fischerworks.mobdoctoring.client.model;

import com.fischerworks.mobdoctoring.entities.GhostSpiderEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import java.util.function.Function;

public class GhostSpiderModel<T extends GhostSpiderEntity> extends EntityModel<T> {
    private ModelRenderer head;
    private ModelRenderer body0;
    private ModelRenderer body1;
    private ModelRenderer spiderLeg1;
    private ModelRenderer spiderLeg2;
    private ModelRenderer spiderLeg3;
    private ModelRenderer spiderLeg4;
    private ModelRenderer spiderLeg5;
    private ModelRenderer spiderLeg6;
    private ModelRenderer spiderLeg7;
    private ModelRenderer spiderLeg8;

    public GhostSpiderModel(Function<ResourceLocation, RenderType> p_i225945_1_) {
        super(p_i225945_1_);
        setupLimbs();
    }

    void setupLimbs() {
        textureWidth = 64;
        textureHeight = 32;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 15.0F, -3.0F);
        head.setTextureOffset(32, 4).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);

        body0 = new ModelRenderer(this);
        body0.setRotationPoint(0.0F, 15.0F, 0.0F);
        body0.setTextureOffset(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, true);

        body1 = new ModelRenderer(this);
        body1.setRotationPoint(0.0F, 15.0F, 9.0F);
        body1.setTextureOffset(0, 12).addBox(-5.0F, -4.0F, -6.0F, 10.0F, 8.0F, 12.0F, 0.0F, true);

        spiderLeg1 = new ModelRenderer(this);
        spiderLeg1.setRotationPoint(4.0F, 15.0F, 2.0F);
        spiderLeg1.setTextureOffset(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, true);

        spiderLeg2 = new ModelRenderer(this);
        spiderLeg2.setRotationPoint(-4.0F, 15.0F, 2.0F);
        spiderLeg2.setTextureOffset(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, true);

        spiderLeg3 = new ModelRenderer(this);
        spiderLeg3.setRotationPoint(4.0F, 15.0F, 1.0F);
        spiderLeg3.setTextureOffset(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, true);

        spiderLeg4 = new ModelRenderer(this);
        spiderLeg4.setRotationPoint(-4.0F, 15.0F, 1.0F);
        spiderLeg4.setTextureOffset(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, true);

        spiderLeg5 = new ModelRenderer(this);
        spiderLeg5.setRotationPoint(4.0F, 15.0F, 0.0F);
        spiderLeg5.setTextureOffset(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, true);

        spiderLeg6 = new ModelRenderer(this);
        spiderLeg6.setRotationPoint(-4.0F, 15.0F, 0.0F);
        spiderLeg6.setTextureOffset(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, true);

        spiderLeg7 = new ModelRenderer(this);
        spiderLeg7.setRotationPoint(4.0F, 15.0F, -1.0F);
        spiderLeg7.setTextureOffset(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, true);

        spiderLeg8 = new ModelRenderer(this);
        spiderLeg8.setRotationPoint(-4.0F, 15.0F, -1.0F);
        spiderLeg8.setTextureOffset(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, true);

    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        float f = ((float)Math.PI / 4F);
        this.spiderLeg1.rotateAngleZ = ((float)Math.PI / 4F);
        this.spiderLeg2.rotateAngleZ = (-(float)Math.PI / 4F);
        this.spiderLeg3.rotateAngleZ = 0.58119464F;
        this.spiderLeg4.rotateAngleZ = -0.58119464F;
        this.spiderLeg5.rotateAngleZ = 0.58119464F;
        this.spiderLeg6.rotateAngleZ = -0.58119464F;
        this.spiderLeg7.rotateAngleZ = ((float)Math.PI / 4F);
        this.spiderLeg8.rotateAngleZ = (-(float)Math.PI / 4F);
        float f1 = -0.0F;
        float f2 = ((float)Math.PI / 8F);
        this.spiderLeg1.rotateAngleY = ((float)Math.PI / 4F);
        this.spiderLeg2.rotateAngleY = (-(float)Math.PI / 4F);
        this.spiderLeg3.rotateAngleY = ((float)Math.PI / 8F);
        this.spiderLeg4.rotateAngleY = (-(float)Math.PI / 8F);
        this.spiderLeg5.rotateAngleY = (-(float)Math.PI / 8F);
        this.spiderLeg6.rotateAngleY = ((float)Math.PI / 8F);
        this.spiderLeg7.rotateAngleY = (-(float)Math.PI / 4F);
        this.spiderLeg8.rotateAngleY = ((float)Math.PI / 4F);
        float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float f4 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f5 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f6 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
        float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        float f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f9 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f10 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
        this.spiderLeg1.rotateAngleY += f3;
        this.spiderLeg2.rotateAngleY += -f3;
        this.spiderLeg3.rotateAngleY += f4;
        this.spiderLeg4.rotateAngleY += -f4;
        this.spiderLeg5.rotateAngleY += f5;
        this.spiderLeg6.rotateAngleY += -f5;
        this.spiderLeg7.rotateAngleY += f6;
        this.spiderLeg8.rotateAngleY += -f6;
        this.spiderLeg1.rotateAngleZ += f7;
        this.spiderLeg2.rotateAngleZ += -f7;
        this.spiderLeg3.rotateAngleZ += f8;
        this.spiderLeg4.rotateAngleZ += -f8;
        this.spiderLeg5.rotateAngleZ += f9;
        this.spiderLeg6.rotateAngleZ += -f9;
        this.spiderLeg7.rotateAngleZ += f10;
        this.spiderLeg8.rotateAngleZ += -f10;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body0.render(matrixStack, buffer, packedLight, packedOverlay);
        body1.render(matrixStack, buffer, packedLight, packedOverlay);
        spiderLeg1.render(matrixStack, buffer, packedLight, packedOverlay);
        spiderLeg2.render(matrixStack, buffer, packedLight, packedOverlay);
        spiderLeg3.render(matrixStack, buffer, packedLight, packedOverlay);
        spiderLeg4.render(matrixStack, buffer, packedLight, packedOverlay);
        spiderLeg5.render(matrixStack, buffer, packedLight, packedOverlay);
        spiderLeg6.render(matrixStack, buffer, packedLight, packedOverlay);
        spiderLeg7.render(matrixStack, buffer, packedLight, packedOverlay);
        spiderLeg8.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
