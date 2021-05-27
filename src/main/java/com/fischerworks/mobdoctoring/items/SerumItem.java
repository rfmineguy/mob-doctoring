package com.fischerworks.mobdoctoring.items;

import com.fischerworks.mobdoctoring.potions.side_effects.ImmobilizedEffect;
import com.fischerworks.mobdoctoring.potions.side_effects.SlowDeathEffect;
import com.fischerworks.mobdoctoring.util.ModUtil;
import com.fischerworks.mobdoctoring.util.SerumType;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.minecraft.block.CauldronBlock.LEVEL;

/*
    if type ANTIBODY and HasSideEffect (typeof SlowDeathEffect):
      apply uber saturation (restore hunger) (amplifier 100)
    else
      don't apply saturation
 */
public class SerumItem extends Item {
    private final SerumType type;

    public SerumItem(SerumType type, Item.Properties properties) {
        super(properties);
        this.type = type;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {

        if (entityLiving instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
            serverplayerentity.addStat(Stats.ITEM_USED.get(this));
        }

        if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.isCreativeMode) {
            stack.shrink(1);
        }

        if (entityLiving instanceof PlayerEntity) {
            if (type != SerumType.ANTIBODY) {
                Effect effect = ModUtil.getSerumEffect(type);
                if (effect != null) entityLiving.addPotionEffect(new EffectInstance(effect, 60 * 20));

                if (ModUtil.hasSerumEffect(entityLiving, type) && entityLiving.getRNG().nextFloat() > 0.6f) {
                    Effect effect1 = ModUtil.getSideEffect();
                    if (effect1 != null) {
                        int amplifier = effect1 instanceof ImmobilizedEffect ? 1 : 19;
                        entityLiving.addPotionEffect(new EffectInstance(effect1, 60 * 20, amplifier));
                    }
                }
            } else {
                //see top of file for functionality outline
                if (ModUtil.entityHasSideEffect(entityLiving) != null) {
                    System.out.println("You have a side effect! Congrats! Get healed!");
                    boolean healHunger = ModUtil.entityHasSideEffect(entityLiving) instanceof SlowDeathEffect;
                    ModUtil.clearSideEffects(entityLiving, healHunger);
                }
            }
        }

        return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        return DrinkHelper.startDrinking(worldIn, playerIn, handIn);
    }
}
