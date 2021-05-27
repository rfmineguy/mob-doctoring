package com.fischerworks.mobdoctoring.init;

import com.fischerworks.mobdoctoring.MobDoctoring;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeInit {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_REG = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MobDoctoring.MOD_ID);

}
