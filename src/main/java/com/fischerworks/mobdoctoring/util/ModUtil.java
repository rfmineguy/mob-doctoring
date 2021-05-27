package com.fischerworks.mobdoctoring.util;

import com.fischerworks.mobdoctoring.MobDoctoring;
import com.fischerworks.mobdoctoring.init.EffectInit;
import com.fischerworks.mobdoctoring.potions.MobImmunityEffect;
import com.fischerworks.mobdoctoring.potions.side_effects.SideEffect;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.vector.Vector3i;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ModUtil {
    private static final List<String> COMMON = new ArrayList<>();
    private static final List<String> DANGEROUS = new ArrayList<>();
    private static final List<String> VIOLENT = new ArrayList<>();
    private static final List<String> IMPOSSIBLE = new ArrayList<>();
    private static final ResourceLocation LIGHT_OVERLAY = new ResourceLocation(MobDoctoring.MOD_ID, "textures/misc/edge_vision.png");
    private static final ResourceLocation DARK_OVERLAY = new ResourceLocation(MobDoctoring.MOD_ID, "textures/misc/dark_effect.png");

    public static void init() {
        COMMON.add("minecraft:zombie");
        COMMON.add("minecraft:drowned");
        COMMON.add("minecraft:zombie_villager");
        COMMON.add("minecraft:skeleton");
        COMMON.add("minecraft:spider");
        COMMON.add("minecraft:cave_spider");
        COMMON.add("minecraft:slime");
        COMMON.add("minecraft:husk");

        DANGEROUS.add("minecraft:blaze");
        DANGEROUS.add("minecraft:creeper");
        DANGEROUS.add("minecraft:enderman");
        DANGEROUS.add("minecraft:guardian");
        DANGEROUS.add("minecraft:zombified_piglin"); //?
        DANGEROUS.add("minecraft:piglin");
        DANGEROUS.add("minecraft:magma_cube");
        DANGEROUS.add("minecraft:ghast");
        DANGEROUS.add("minecraft:shulker");

        VIOLENT.add("minecraft:illusioner");
        VIOLENT.add("minecraft:pillager");
        VIOLENT.add("minecraft:phantom");
        VIOLENT.add("minecraft:wither_skeleton");
        VIOLENT.add("minecraft:hoglin");
        VIOLENT.add("minecraft:stray");
        VIOLENT.add("minecraft:vindicator");
        VIOLENT.add("minecraft:zoglin");
        VIOLENT.add("minecraft:endermite");

        IMPOSSIBLE.add("minecraft:evoker");
        IMPOSSIBLE.add("minecraft:elder_guardian");
        IMPOSSIBLE.add("minecraft:ravager");
        IMPOSSIBLE.add("minecraft:vex");
        IMPOSSIBLE.add("minecraft:ender_dragon");
        IMPOSSIBLE.add("minecraft:wither");
    }

    //returns true if the specified entity is of the same or a higher tier than the sword
    public static boolean isLivingEntityOfAtLeastTier(MobDoctoringTier tier, String entity) {
        if (tier == MobDoctoringTier.IMPOSSIBLE) {
            return COMMON.contains(entity) || DANGEROUS.contains(entity) || VIOLENT.contains(entity) || IMPOSSIBLE.contains(entity);
        } else if (tier == MobDoctoringTier.VIOLENT) {
            return COMMON.contains(entity) || DANGEROUS.contains(entity) || VIOLENT.contains(entity);
        } else if (tier == MobDoctoringTier.DANGEROUS) {
            return COMMON.contains(entity) || DANGEROUS.contains(entity);
        } else {
            return COMMON.contains(entity);
        }
    }

    public static MobDoctoringTier getTier(String entity) {
        if (COMMON.contains(entity)) return MobDoctoringTier.COMMON;
        if (DANGEROUS.contains(entity)) return MobDoctoringTier.DANGEROUS;
        if (VIOLENT.contains(entity)) return MobDoctoringTier.VIOLENT;
        if (IMPOSSIBLE.contains(entity)) return MobDoctoringTier.IMPOSSIBLE;
        else return null;
    }

    //null if not an implemented entity yet
    public static Effect getRequiredEffect(MonsterEntity entity) {
        EntityType<?> entityType = entity.getType();
        if (entityType == EntityType.ZOMBIE || entityType == EntityType.ZOMBIE_VILLAGER || entityType == EntityType.ZOMBIFIED_PIGLIN || entityType == EntityType.DROWNED || entityType == EntityType.HUSK) { return EffectInit.UNDEAD_PROTECTION_EFFECT.get(); }
        else if (entityType == EntityType.SKELETON || entityType == EntityType.WITHER_SKELETON) return EffectInit.BONES_PROTECTION_EFFECT.get();
        else if (entityType == EntityType.SPIDER || entityType == EntityType.CAVE_SPIDER) return EffectInit.ARACHNIDS_PROTECTION_EFFECT.get();
        else if (entityType == EntityType.SLIME) return EffectInit.SLIME_PROTECTION_EFFECT.get();
        else if (entityType == EntityType.BLAZE) return EffectInit.BLAZE_PROTECTION_EFFECT.get();
        else if (entityType == EntityType.CREEPER) return EffectInit.CREEPY_PROTECTION_EFFECT.get();
        else if (entityType == EntityType.ENDERMAN) return EffectInit.ENDER_PROTECTION_EFFECT.get();
        else if (entityType == EntityType.GUARDIAN || entityType == EntityType.ELDER_GUARDIAN) return EffectInit.GUARDIAN_PROTECTION_EFFECT.get();
        else if (entityType == EntityType.PIGLIN || entityType == EntityType.ZOMBIFIED_PIGLIN) return EffectInit.PIGLIN_PROTECTION_EFFECT.get();
        else if (entityType == EntityType.MAGMA_CUBE) return EffectInit.MAGMA_PROTECTION_EFFECT.get();
        else if (entityType == EntityType.GHAST) return EffectInit.GHAST_PROTECTION_EFFECT.get();
        else return (entityType == EntityType.SHULKER || entityType == EntityType.SHULKER_BULLET) ? EffectInit.SHULK_PROTECTION_EFFECT.get() : null;
    }
    public static Effect getSerumEffect(SerumType type) {
        switch (type) {
            case UNDEAD:    return EffectInit.UNDEAD_PROTECTION_EFFECT.get();
            case BONES:     return EffectInit.BONES_PROTECTION_EFFECT.get();
            case ARACHNIDS: return EffectInit.ARACHNIDS_PROTECTION_EFFECT.get();
            case SLIME:     return EffectInit.SLIME_PROTECTION_EFFECT.get();
            case BLAZE:     return EffectInit.BLAZE_PROTECTION_EFFECT.get();
            case CREEPY:    return EffectInit.CREEPY_PROTECTION_EFFECT.get();
            case ENDER:     return EffectInit.ENDER_PROTECTION_EFFECT.get();
            case GUARDIAN:  return EffectInit.GUARDIAN_PROTECTION_EFFECT.get();
            case PIGLIN:    return EffectInit.PIGLIN_PROTECTION_EFFECT.get();
            case MAGMA:     return EffectInit.MAGMA_PROTECTION_EFFECT.get();
            case GHAST:     return EffectInit.GHAST_PROTECTION_EFFECT.get();
            case SHULK:     return EffectInit.SHULK_PROTECTION_EFFECT.get();
        }
        return null;
    }
    public static boolean hasSerumEffect(LivingEntity entity, SerumType excludeType) {
        for (EffectInstance effect : entity.getActivePotionEffects()) {
            if (effect.getPotion() instanceof MobImmunityEffect && ((MobImmunityEffect) effect.getPotion()).getSerumType() != excludeType) return true;
        }
        return false;
    }

    //if two serums are applied, have a chance to get side effects (from the eight randomly)
    public static SideEffect getSideEffect() {
        Random random = new Random();
        switch (random.nextInt(5)) {
            case 0: return (SideEffect) EffectInit.SLOW_CERTAIN_DEATH.get();
            case 1: return (SideEffect) EffectInit.IMMOBILIZED_EFFECT.get();
            case 2: return (SideEffect) EffectInit.CENTER_VISION_EFFECT.get();
            case 3: return (SideEffect) EffectInit.EDGE_VISION_EFFECT.get();
            case 4: return (SideEffect) EffectInit.HALLUCINATIONS_EFFECT.get();
            default: return null;
        }
    }
    public static boolean entityHasEffect(LivingEntity entity, Effect effect) {
        EffectInstance effect1 = entity.getActivePotionEffect(effect);
        if (effect1 == null) return false;
        return effect1.getPotion().equals(effect);
    }
    public static SideEffect entityHasSideEffect(LivingEntity entity) {
        for (EffectInstance effect : entity.getActivePotionEffects()) {
            if (effect.getPotion() instanceof SideEffect) return (SideEffect) effect.getPotion();
        }
        return null;
    }
    public static void clearSideEffects(LivingEntity entity, boolean healHunger) {
        if (entity.getActivePotionEffects().size() == 0) return;
        Consumer<EffectInstance> style = (EffectInstance e) -> {
            e.getPotion().removeAttributesModifiersFromEntity(entity, entity.getAttributeManager(), e.getAmplifier());
        };
        entity.getActivePotionEffects().forEach(style);
        entity.getActivePotionEffects().removeIf(effect -> effect.getPotion() instanceof SideEffect);
        if (healHunger) {
            entity.addPotionEffect(new EffectInstance(Effects.SATURATION, 1, 100));
        }
    }
    public static Vector3i RandomOffsetPosition(int range) {
        Random random = new Random();
        return new Vector3i(random.nextInt(range * 2) - range, 0, random.nextInt(range * 2) - range);
    }
}