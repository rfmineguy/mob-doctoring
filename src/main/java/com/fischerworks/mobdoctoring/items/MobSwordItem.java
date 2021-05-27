package com.fischerworks.mobdoctoring.items;

import com.fischerworks.mobdoctoring.util.MobDoctoringTier;
import com.fischerworks.mobdoctoring.util.ModUtil;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TieredItem;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class MobSwordItem extends SwordItem {
    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public MobSwordItem(MobDoctoringTier tierIn, Properties builderIn) {
        super(tierIn, (int) tierIn.getAttackDamage(), tierIn.getAttackSpeed(), builderIn);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        this.attributeModifiers = builder.build();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        String entityId = target.getEntityString(); // minecraft:creeper, etc
        int durabilityDealt = 0;

        if (ModUtil.isLivingEntityOfAtLeastTier(getTier(), entityId)) {
            //deal 1.5x base vanilla tier damage
            //don't do damage to weapon
            target.attackEntityFrom(DamageSource.GENERIC, getAttackDefault() * getTier().highEnoughTierMultiplier());
            System.out.println(getTier().highEnoughTierMultiplier() + "x damage dealt : " + getAttackDefault() * getTier().highEnoughTierMultiplier());
        } else {
            //deal default iron/diamond/netherrite damage
            target.attackEntityFrom(DamageSource.GENERIC, getAttackDefault());
            System.out.println("1x damage dealt : " + getAttackDefault());

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
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative() || state == Blocks.COBWEB.getDefaultState();
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.matchesBlock(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            Material material = state.getMaterial();
            return material != Material.PLANTS && material != Material.TALL_PLANTS && material != Material.CORAL && !state.isIn(BlockTags.LEAVES) && material != Material.GOURD ? 1.0F : 1.5F;
        }
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (state.getBlockHardness(worldIn, pos) != 0.0F) {
            stack.damageItem(2, entityLiving, (entity) -> {
                entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
            });
        }
        return true;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return this.attributeModifiers;
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
        return blockIn.matchesBlock(Blocks.COBWEB);
    }

    public float getAttackDefault() {
        return getTier().getDefaultDamage();
    }

    @Override
    public MobDoctoringTier getTier() {
        return (MobDoctoringTier) super.getTier();
    }
}
