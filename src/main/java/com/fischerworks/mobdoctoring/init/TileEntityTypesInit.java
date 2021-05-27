package com.fischerworks.mobdoctoring.init;

import com.fischerworks.mobdoctoring.MobDoctoring;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypesInit {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_REG = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MobDoctoring.MOD_ID);
}
