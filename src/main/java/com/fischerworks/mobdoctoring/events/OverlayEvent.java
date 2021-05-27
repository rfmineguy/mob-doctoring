package com.fischerworks.mobdoctoring.events;

import com.fischerworks.mobdoctoring.MobDoctoring;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OverlayEvent {
    public enum ActiveEffect { NONE, CENTER, EDGE }

    public static final ResourceLocation TRANSPARENT = new ResourceLocation(MobDoctoring.MOD_ID, "textures/misc/transparent.png");
    public static final ResourceLocation CENTER_VISION_OVERLAY = new ResourceLocation(MobDoctoring.MOD_ID, "textures/misc/center_vision.png");
    public static final ResourceLocation EDGE_VISION_OVERLAY = new ResourceLocation(MobDoctoring.MOD_ID, "textures/misc/edge_vision.png");
    private static int scaledWidth, scaledHeight;
    public static ActiveEffect activeEffect = ActiveEffect.NONE;

    @SubscribeEvent
    public static void overlay(RenderGameOverlayEvent.Pre event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            switch (activeEffect) {
                case NONE: Minecraft.getInstance().getTextureManager().bindTexture(TRANSPARENT);
                    return;
                case CENTER: Minecraft.getInstance().getTextureManager().bindTexture(CENTER_VISION_OVERLAY);
                    break;
                case EDGE: Minecraft.getInstance().getTextureManager().bindTexture(EDGE_VISION_OVERLAY);
                    break;
//                case DARK: Minecraft.getInstance().getTextureManager().bindTexture();
            }
            renderSelectedEffect(0.9f);
        }
    }

    private static void renderSelectedEffect(float alpha) {
        calc();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableBlend();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder builder = tessellator.getBuffer();
        builder.begin(7, DefaultVertexFormats.POSITION_COLOR_TEX);
        builder.pos(0.0D, scaledHeight, -90.0D).color(1, 1, 1, alpha).tex(0.0F, 1.0F).endVertex();
        builder.pos(scaledWidth, scaledHeight, -90.0D).color(1, 1, 1, alpha).tex(1.0F, 1.0F).endVertex();
        builder.pos(scaledWidth, 0.0D, -90.0D).color(1, 1, 1, alpha).tex(1.0F, 0.0F).endVertex();
        builder.pos(0.0D, 0.0D, -90.0D).color(1, 1, 1, alpha).tex(0.0F, 0.0F).endVertex();
        tessellator.draw();

        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
    }

    private static void calc() {
        scaledWidth = Minecraft.getInstance().getMainWindow().getScaledWidth();
        scaledHeight = Minecraft.getInstance().getMainWindow().getScaledHeight();
    }
}
