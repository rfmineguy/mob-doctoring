package com.fischerworks.mobdoctoring.events;

import com.fischerworks.mobdoctoring.init.ItemInit;
import com.fischerworks.mobdoctoring.util.MobDoctoringTier;
import com.fischerworks.mobdoctoring.util.ModUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonsterEntityEvents {

    @SubscribeEvent
    public static void entityAttackEvent(LivingSetAttackTargetEvent event) {
        LivingEntity target = event.getTarget();            //the player
        LivingEntity attacker = event.getEntityLiving();    //the mob attacking
        if(target instanceof PlayerEntity && attacker instanceof MonsterEntity) {
            Effect requiredEffect = ModUtil.getRequiredEffect((MonsterEntity) attacker);
            if (ModUtil.entityHasEffect(target, requiredEffect)) {
                ((MobEntity)attacker).setAttackTarget(null);
                ((MobEntity)attacker).setAggroed(false);
            }
        }
    }

    @SubscribeEvent
    public static void entityDeathEvent(LivingDeathEvent event) {
        String entityString = event.getEntityLiving().getEntityString();
        MobDoctoringTier targetEntityTier = ModUtil.getTier(entityString);
        if (targetEntityTier == null) return;
        switch (targetEntityTier) {
            case COMMON: event.getEntity().entityDropItem(ItemInit.COMMON_SPECIMEN.get());
                break;
            case DANGEROUS: event.getEntity().entityDropItem(ItemInit.DANGEROUS_SPECIMEN.get());
                break;
            case VIOLENT: event.getEntity().entityDropItem(ItemInit.VIOLENT_SPECIMEN.get());
                break;
            case IMPOSSIBLE: event.getEntity().entityDropItem(ItemInit.IMPOSSIBLE_SPECIMEN.get());
                break;
            default: //do nothing
        }
    }
}