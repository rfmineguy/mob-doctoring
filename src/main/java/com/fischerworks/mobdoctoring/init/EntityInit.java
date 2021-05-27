package com.fischerworks.mobdoctoring.init;

import com.fischerworks.mobdoctoring.MobDoctoring;
import com.fischerworks.mobdoctoring.entities.GhostCreeperEntity;
import com.fischerworks.mobdoctoring.entities.GhostSkeletonEntity;
import com.fischerworks.mobdoctoring.entities.GhostSpiderEntity;
import com.fischerworks.mobdoctoring.entities.GhostZombieEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPE_REG = DeferredRegister.create(ForgeRegistries.ENTITIES, MobDoctoring.MOD_ID);

    public static RegistryObject<EntityType<GhostCreeperEntity>>    GHOST_CREEPER_ENTITY  = ENTITY_TYPE_REG.register("ghost_creeper", () -> EntityType.Builder.create(GhostCreeperEntity::new, EntityClassification.MONSTER).size(0.6F, 1.7F).build(new ResourceLocation(MobDoctoring.MOD_ID, "ghost_creeper").toString()));
    public static RegistryObject<EntityType<GhostZombieEntity>>     GHOST_ZOMBIE_ENTITY   = ENTITY_TYPE_REG.register("ghost_zombie", () -> EntityType.Builder.create(GhostZombieEntity::new, EntityClassification.MONSTER).size(0.6F, 1.7F).build(new ResourceLocation(MobDoctoring.MOD_ID, "ghost_zombie").toString()));
    public static RegistryObject<EntityType<GhostSpiderEntity>>     GHOST_SPIDER_ENTITY   = ENTITY_TYPE_REG.register("ghost_spider", () -> EntityType.Builder.create(GhostSpiderEntity::new, EntityClassification.MONSTER).size(0.6F, 1.7F).build(new ResourceLocation(MobDoctoring.MOD_ID, "ghost_spider").toString()));
    public static RegistryObject<EntityType<GhostSkeletonEntity>>   GHOST_SKELETON_ENTITY = ENTITY_TYPE_REG.register("ghost_skeleton", () -> EntityType.Builder.create(GhostSkeletonEntity::new, EntityClassification.MONSTER).size(0.6f, 1.7f).build(new ResourceLocation(MobDoctoring.MOD_ID, "ghost_skeleton").toString()));
}
