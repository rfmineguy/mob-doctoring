package com.fischerworks.mobdoctoring.potions.side_effects;

import com.fischerworks.mobdoctoring.entities.GhostCreeperEntity;
import com.fischerworks.mobdoctoring.entities.GhostSkeletonEntity;
import com.fischerworks.mobdoctoring.entities.GhostSpiderEntity;
import com.fischerworks.mobdoctoring.entities.GhostZombieEntity;
import com.fischerworks.mobdoctoring.init.EntityInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HallucinationsEffect extends SideEffect {
    ArrayList<MonsterEntity> spawnedMonsters = new ArrayList<>();

    public HallucinationsEffect() {
        super(EffectType.HARMFUL, 0xffffff);
    }

    //add visual monsters around the player which disappear when punched, ideally not visible to other players
    //
    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
        int numberOfMonsters = entityLivingBaseIn.getRNG().nextInt(8);
        for (int i = 0; i < numberOfMonsters; i++) {
            spawnRandomMob(entityLivingBaseIn.world, entityLivingBaseIn.getPosX(), entityLivingBaseIn.getPosY(), entityLivingBaseIn.getPosZ());
        }
    }

    private void spawnRandomMob(World world, double x, double y, double z) {
        MonsterEntity entity = null;
        int spawnMob = world.getRandom().nextInt(5); //1 -> 3
        if (spawnMob == 4) entity = new GhostSkeletonEntity(EntityInit.GHOST_SKELETON_ENTITY.get(), world);
        else if (spawnMob == 3) entity = new GhostZombieEntity(EntityInit.GHOST_ZOMBIE_ENTITY.get(), world);
        else if (spawnMob == 2) entity = new GhostSpiderEntity(EntityInit.GHOST_SPIDER_ENTITY.get(), world);
        else if (spawnMob == 1) entity = new GhostCreeperEntity(EntityInit.GHOST_CREEPER_ENTITY.get(), world);
        else return;
        spawnEntity(world, entity, x, y, z);
    }

    private void spawnEntity(World world, MonsterEntity entity, double x, double y, double z) {
        entity.setPosition(x, y, z);
        world.addEntity(entity);
        spawnedMonsters.add(entity);
    }

    //time between waves
    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % 120 == 0;
    }

    @Override
    public boolean isInstant() {
        return false;
    }
}
