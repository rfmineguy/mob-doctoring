package com.fischerworks.mobdoctoring.init;

import com.fischerworks.mobdoctoring.MobDoctoring;
import com.fischerworks.mobdoctoring.potions.MobImmunityEffect;
import com.fischerworks.mobdoctoring.potions.side_effects.*;
import com.fischerworks.mobdoctoring.util.SerumType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInit {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, MobDoctoring.MOD_ID);
    public static final RegistryObject<Effect> UNDEAD_PROTECTION_EFFECT     = EFFECTS.register("undead_protection",     () -> new MobImmunityEffect(SerumType.UNDEAD, 0x594711));
    public static final RegistryObject<Effect> BONES_PROTECTION_EFFECT      = EFFECTS.register("bones_protection",      () -> new MobImmunityEffect(SerumType.BONES, 0xc2c1bd));
    public static final RegistryObject<Effect> ARACHNIDS_PROTECTION_EFFECT  = EFFECTS.register("arachnids_protection",  () -> new MobImmunityEffect(SerumType.ARACHNIDS, 0xdb0635));
    public static final RegistryObject<Effect> SLIME_PROTECTION_EFFECT      = EFFECTS.register("slime_protection",      () -> new MobImmunityEffect(SerumType.SLIME, 0x5cd44d));
    public static final RegistryObject<Effect> BLAZE_PROTECTION_EFFECT      = EFFECTS.register("blaze_protection",      () -> new MobImmunityEffect(SerumType.BLAZE, 0x14695b));
    public static final RegistryObject<Effect> CREEPY_PROTECTION_EFFECT     = EFFECTS.register("creepy_protection",     () -> new MobImmunityEffect(SerumType.CREEPY, 0x14695b));
    public static final RegistryObject<Effect> ENDER_PROTECTION_EFFECT      = EFFECTS.register("ender_protection",      () -> new MobImmunityEffect(SerumType.ENDER, 0x14695b));
    public static final RegistryObject<Effect> GUARDIAN_PROTECTION_EFFECT   = EFFECTS.register("guardian_protection",   () -> new MobImmunityEffect(SerumType.GUARDIAN, 0x6cc5ac));
    public static final RegistryObject<Effect> PIGLIN_PROTECTION_EFFECT     = EFFECTS.register("piglin_protection",     () -> new MobImmunityEffect(SerumType.PIGLIN, 0xffe800));
    public static final RegistryObject<Effect> MAGMA_PROTECTION_EFFECT      = EFFECTS.register("magma_protection",      () -> new MobImmunityEffect(SerumType.MAGMA, 0xeaa727));
    public static final RegistryObject<Effect> GHAST_PROTECTION_EFFECT      = EFFECTS.register("ghast_protection",      () -> new MobImmunityEffect(SerumType.GHAST, 0x9fc3c3));
    public static final RegistryObject<Effect> SHULK_PROTECTION_EFFECT      = EFFECTS.register("shulk_protection",      () -> new MobImmunityEffect(SerumType.SHULK, 0x976997));

    //use amplifier 18 for slight movement, and 20 for no movement (disable zoom?)
    public static final RegistryObject<Effect> IMMOBILIZED_EFFECT                       = EFFECTS.register("effect_immobilized", () -> new ImmobilizedEffect().addAttributesModifier(Attributes.MOVEMENT_SPEED, "09d8b086-b03f-11eb-8529-0242ac130003", (double)-0.05f, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<Effect> SLOW_CERTAIN_DEATH                       = EFFECTS.register("effect_certaindeath", SlowDeathEffect::new);
    public static final RegistryObject<Effect> CENTER_VISION_EFFECT                     = EFFECTS.register("effect_center_tunnel", CenterTunnelVisionEffect::new);
    public static final RegistryObject<Effect> EDGE_VISION_EFFECT                       = EFFECTS.register("effect_edge_tunnel", EdgeTunnelVisionEffect::new);
    public static final RegistryObject<Effect> HALLUCINATIONS_EFFECT                    = EFFECTS.register("effect_hallucinations", HallucinationsEffect::new);

    public static void registerAllRecipes() {
        /*addBrewingRecipe(Items.ROTTEN_FLESH,        ItemInit.COMMON_ESSENCE.get(), ItemInit.UNDEAD_SERUM.get());
        addBrewingRecipe(Items.BONE,                ItemInit.COMMON_ESSENCE.get(), ItemInit.BONES_SERUM.get());
        addBrewingRecipe(Items.SPIDER_EYE,          ItemInit.COMMON_ESSENCE.get(), ItemInit.ARACHNID_SERUM.get());
        addBrewingRecipe(Items.SLIME_BALL,          ItemInit.COMMON_ESSENCE.get(), ItemInit.SLIME_SERUM.get());
        addBrewingRecipe(Items.BLAZE_POWDER,        ItemInit.DANGEROUS_ESSENCE.get(), ItemInit.BLAZE_SERUM.get());
        addBrewingRecipe(Items.GUNPOWDER,           ItemInit.DANGEROUS_ESSENCE.get(), ItemInit.CREEPY_SERUM.get());
        addBrewingRecipe(Items.ENDER_PEARL,         ItemInit.DANGEROUS_ESSENCE.get(), ItemInit.ENDER_SERUM.get());
        addBrewingRecipe(Items.PRISMARINE_SHARD,    ItemInit.DANGEROUS_ESSENCE.get(), ItemInit.GUARDIAN_SERUM.get());
        addBrewingRecipe(Items.GOLDEN_SWORD,        ItemInit.DANGEROUS_ESSENCE.get(), ItemInit.PIGLIN_SERUM.get());
        addBrewingRecipe(Items.MAGMA_CREAM,         ItemInit.DANGEROUS_ESSENCE.get(), ItemInit.MAGMA_SERUM.get());
        addBrewingRecipe(Items.GHAST_TEAR,          ItemInit.DANGEROUS_ESSENCE.get(), ItemInit.GHAST_SERUM.get());
        addBrewingRecipe(Items.SHULKER_SHELL,       ItemInit.DANGEROUS_ESSENCE.get(), ItemInit.SHULK_SERUM.get());
         */

        addBrewingRecipe(ItemInit.COMMON_ESSENCE.get(), Items.ROTTEN_FLESH, ItemInit.UNDEAD_SERUM.get());
        addBrewingRecipe(ItemInit.COMMON_ESSENCE.get(), Items.SPIDER_EYE, ItemInit.ARACHNID_SERUM.get());
    }

    private static void addBrewingRecipe(Item input, Item ingredient, Item outputItem) {
        BrewingRecipeRegistry.addRecipe(
                Ingredient.fromItems(input),
                Ingredient.fromItems(ingredient),
                new ItemStack(outputItem.getItem().asItem())
        );
    }
}
