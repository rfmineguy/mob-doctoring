package com.fischerworks.mobdoctoring.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class GhostZombieEntity extends MonsterEntity {
    public GhostZombieEntity(EntityType<? extends GhostZombieEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 8.0F, 1.f));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public boolean hitByEntity(Entity entityIn) {
        //remove entity from world on hit
        this.remove();
        return super.hitByEntity(entityIn);
    }

    @Override
    public float getHealth() {
        return 20;
    }
}