package com.fischerworks.mobdoctoring.items;

import com.fischerworks.mobdoctoring.util.MobDoctoringTier;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import com.google.common.collect.ImmutableMultimap.Builder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import java.util.List;

public class SwordTest extends SwordItem {
    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public SwordTest(MobDoctoringTier tier, Properties builderIn) {
        super(tier, (int)tier.getAttackDamage(), tier.getAttackSpeed(), builderIn);
        Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        this.attributeModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return this.attributeModifiers;
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

    }
}
