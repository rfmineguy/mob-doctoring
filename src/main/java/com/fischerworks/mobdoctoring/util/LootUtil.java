package com.fischerworks.mobdoctoring.util;

import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;

public final class LootUtil {

    /**
     * Code that handles injecting stuff into chest loot pools.
     * Based heavily on SimpleCoreLib which is based on Botania's LootHandler, without which I never would have
     * figured this out.
     *
     */
    public static LootPool getInjectPool(String modid, String entryName)
    {
        return LootPool.builder()
                .addEntry(getInjectEntry(modid, entryName, 1))
                .bonusRolls(0, 1)
                .name(modid + "_inject")
                .build();
    }

    private static LootEntry.Builder<?> getInjectEntry(String modid, String name, int weight) {
        ResourceLocation table = new ResourceLocation(modid, "inject/" + name);
        return TableLootEntry.builder(table).weight(weight);
    }
}
