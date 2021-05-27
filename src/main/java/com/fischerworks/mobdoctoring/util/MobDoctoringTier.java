package com.fischerworks.mobdoctoring.util;

import com.fischerworks.mobdoctoring.init.ItemInit;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum MobDoctoringTier implements IItemTier {
    //attackspeed is (4 + x),
    //iron sword is 3 damage

    /*
        WOOD        -> 4 hp (2 hearts)
        GOLD        -> 4 hp (2 hearts)
        STONE       -> 5 hp (2.5 hearts)
        IRON        -> 6 hp (3 hearts)
        DIAMOND     -> 7 hp (3.5 hearts)
        NETHERITE   -> 8 hp (4 hearts)
     */
    //sword total damage = 'attackDamageIn' + tierDamage + 1
    COMMON    (700, -3.4f, 6.0f, 6.f, 2.f, 14, 2),    //similar to iron tier damage
    DANGEROUS (900, -2.8f, 6.0f, 6.f, 2.f, 14, 2),    //similar to iron tier damage
    VIOLENT   (1100, -2.2f, 8.0f, 7.f, 2.f, 10, 3),    //similar to diamond tier damage
    IMPOSSIBLE(1300, -1.6f, 9.0f,  8.0f, 2.f, 15, 4);   //similar to netherite tier damage

    private final int maxUses;
    private final float attackSpeed;
    private final float defaultDamage;
    private final float efficiency;
    private final float highEnoughTierMultiplier;
    private final int enchantability;
    private final int harvestLevel;

    MobDoctoringTier(int maxUses, float attackSpeed, float efficiencyIn, float defaultDamageIn, float highEnoughTierMultiplier, int enchantabilityIn, int harvestLevel) {
        this.maxUses = maxUses;
        this.attackSpeed = attackSpeed;
        this.defaultDamage = defaultDamageIn;
        this.efficiency = efficiencyIn;
        this.highEnoughTierMultiplier = highEnoughTierMultiplier;
        this.enchantability = enchantabilityIn;
        this.harvestLevel = harvestLevel;
    }

    @Override
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.defaultDamage;
    }

    public float getAttackSpeed() {
        return this.attackSpeed;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        if (this.equals(MobDoctoringTier.COMMON)) return Ingredient.fromItems(ItemInit.COMMON_SPECIMEN.get());
        else if (this.equals(MobDoctoringTier.DANGEROUS)) return Ingredient.fromItems(ItemInit.DANGEROUS_SPECIMEN.get());
        else if (this.equals(MobDoctoringTier.VIOLENT)) return Ingredient.fromItems(ItemInit.VIOLENT_SPECIMEN.get());
        else return Ingredient.fromItems(ItemInit.IMPOSSIBLE_SPECIMEN.get());
    }

    public float getDefaultDamage() {
        return defaultDamage;
    }

    public float highEnoughTierMultiplier() {
        return highEnoughTierMultiplier;
    }
}