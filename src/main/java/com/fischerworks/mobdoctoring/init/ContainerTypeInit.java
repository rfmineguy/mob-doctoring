package com.fischerworks.mobdoctoring.init;

import com.fischerworks.mobdoctoring.MobDoctoring;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeInit {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_REG = DeferredRegister.create(ForgeRegistries.CONTAINERS, MobDoctoring.MOD_ID);
}
