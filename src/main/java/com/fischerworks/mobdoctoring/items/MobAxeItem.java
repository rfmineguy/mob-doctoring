package com.fischerworks.mobdoctoring.items;

import com.fischerworks.mobdoctoring.util.MobDoctoringTier;
import com.fischerworks.mobdoctoring.util.ModUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potions;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Set;

public class MobAxeItem extends AxeItem {
    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public MobAxeItem(MobDoctoringTier tierIn, Properties builderIn) {
        super(tierIn, tierIn.getAttackSpeed(), tierIn.getAttackSpeed(), builderIn.addToolType(net.minecraftforge.common.ToolType.AXE, tierIn.getHarvestLevel()));
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        this.attributeModifiers = builder.build();
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        String entityId = target.getEntityString(); // minecraft:creeper, etc
        int durabilityDealt = 0;

        float critMultiplier = getCritMultiplier(attacker);
        if (ModUtil.isLivingEntityOfAtLeastTier(getTier(), entityId)) {
            //deal 1.5x base vanilla tier damage
            //don't do damage to weapon
            target.attackEntityFrom(DamageSource.GENERIC, getAttackDefault() * getTier().highEnoughTierMultiplier() * critMultiplier);
            System.out.println(getTier().highEnoughTierMultiplier() + "x damage dealt : " + getAttackDefault() * getTier().highEnoughTierMultiplier() * critMultiplier);
        } else {
            //deal default iron/diamond/netherrite damage
            target.attackEntityFrom(DamageSource.GENERIC, getAttackDefault() * critMultiplier);
            System.out.println("1x damage dealt : " + getAttackDefault() * critMultiplier);

            //attacking a mob of a higher tier does more and more durability damage
            MobDoctoringTier monsterTier = ModUtil.getTier(entityId);
            if (monsterTier == MobDoctoringTier.COMMON) durabilityDealt = 1;
            else if (monsterTier == MobDoctoringTier.DANGEROUS) durabilityDealt = 2;
            else if (monsterTier == MobDoctoringTier.VIOLENT) durabilityDealt = 3;
            else if (monsterTier == MobDoctoringTier.IMPOSSIBLE) durabilityDealt = 4;
            else durabilityDealt = 1;
        }
        stack.damageItem(durabilityDealt, attacker, (entity) -> {
            entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });
        ((PlayerEntity)attacker).addStat(Stats.ITEM_USED.get(this));
        return true;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return attributeModifiers;
    }

    public float getAttackDefault() {
        return getTier().getDefaultDamage();
    }

    @Override
    public MobDoctoringTier getTier() {
        return (MobDoctoringTier) super.getTier();
    }

    //returns 1 if no crit, and 1.5 if yes crit
    private float getCritMultiplier(LivingEntity entity) {
        return entity.fallDistance > 0.0f
                && !entity.isOnGround()
                && !entity.isOnLadder()
                && !entity.isInWater()
                && !entity.isPotionActive(Effects.BLINDNESS)
                && entity.getRidingEntity() != null ? 1.5f : 1.f;
    }
}
