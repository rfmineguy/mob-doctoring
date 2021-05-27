package com.fischerworks.mobdoctoring.items;

import com.fischerworks.mobdoctoring.init.ItemInit;
import com.fischerworks.mobdoctoring.potions.side_effects.ImmobilizedEffect;
import com.fischerworks.mobdoctoring.util.ModUtil;
import com.fischerworks.mobdoctoring.util.SerumType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.monster.piglin.PiglinBruteEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SyringeItem extends Item {

    private final SerumType type;

    public SyringeItem(SerumType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 16;
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean actionPerformed = false;

        if (type != SerumType.ANTIBODY && target instanceof PlayerEntity) {
            Effect effect = ModUtil.getSerumEffect(type);
            int amplifier = 1;
            if (effect instanceof ImmobilizedEffect) amplifier = 19;
            if (effect != null) {
                target.addPotionEffect(new EffectInstance(effect, 60 * 20, amplifier));
                actionPerformed = true;
            }
        } else if (target instanceof MonsterEntity && type == SerumType.ANTIBODY) {
            //type == ANTIBODY
            World world = attacker.getEntityWorld();
            //change to zombified piglin somehow
            if (target instanceof ZombifiedPiglinEntity) {
                replaceEntity((MonsterEntity) target, new PigEntity(EntityType.PIG, world));
            } else if (target instanceof ZombieVillagerEntity) {
                replaceEntity((MonsterEntity) target, new VillagerEntity(EntityType.VILLAGER, world));
            }
            actionPerformed = true;
        }

        if (actionPerformed && attacker instanceof PlayerEntity) {
            //take one from main stack, give an empty syringe
            int stackSize = attacker.getHeldItem(Hand.MAIN_HAND).getCount();
            int newStackSize = stackSize - 1;
            ((PlayerEntity) attacker).inventory.addItemStackToInventory(new ItemStack(ItemInit.EMPTY_SYRINGE.get()));
            ((PlayerEntity) attacker).getHeldItem(Hand.MAIN_HAND).grow(-1);
        }
        return super.hitEntity(stack, target, attacker);
    }

    private void replaceEntity(MonsterEntity monsterEntity, MobEntity mobEntity) {
        World world = monsterEntity.getEntityWorld();
        BlockPos pos = monsterEntity.getPosition();
        mobEntity.setPosition(pos.getX(), pos.getY(), pos.getZ());
        world.addEntity(mobEntity);
        monsterEntity.remove();
    }
}
