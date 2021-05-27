package com.fischerworks.mobdoctoring.events;

import com.fischerworks.mobdoctoring.init.EntityInit;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import sun.security.provider.ConfigFile;

public class EntityRegisterEvent {

    @SubscribeEvent
    public static void registerEvent(EntityAttributeCreationEvent event) {
        System.out.println("Registering mod entities.");
        event.put(EntityInit.GHOST_CREEPER_ENTITY.get(), createAttributes().create());
        event.put(EntityInit.GHOST_SKELETON_ENTITY.get(), createAttributes().create());
        event.put(EntityInit.GHOST_SPIDER_ENTITY.get(), createAttributes().create());
        event.put(EntityInit.GHOST_ZOMBIE_ENTITY.get(), createAttributes().create());
    }

    private static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 4)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.28D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 1)
                .createMutableAttribute(Attributes.ARMOR, 0)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 0)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 0)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0);
    }
}
