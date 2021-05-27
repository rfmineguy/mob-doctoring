package com.fischerworks.mobdoctoring.events;

import com.fischerworks.mobdoctoring.MobDoctoring;
import com.fischerworks.mobdoctoring.util.InjectionTableLookup;
import com.fischerworks.mobdoctoring.util.LootUtil;
import com.google.gson.JsonParseException;
import net.minecraft.data.loot.ChestLootTables;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LootLoadEvents {
    private static final InjectionTableLookup lookup = new InjectionTableLookup();
    String chestPrefix = "minecraft:chests/";
    String villagerPrefix = "";

    //utilizes code from SimpleCoreLib and SimpleOres on github
    //https://github.com/Sinhika/SimpleOres2/blob/1.16
    //https://github.com/Sinhika/SimpleCoreLib/tree/e6f6bf581dd37b05b64f3a337e1511afb99eb234/src/main/java

    public LootLoadEvents() {
    }


    //add desired loot tables to add to in InjectionTableLookup.java
    @SubscribeEvent
    public void loadLoot(LootTableLoadEvent event) {


        String name = event.getName().toString();
        if (name.startsWith(chestPrefix)) {
            String file = name.substring(name.indexOf(chestPrefix) + chestPrefix.length()); //

            //village chests need special treatment due to their file location
            if (file.startsWith("village/village_")) {
                String village = "village/";
                file = file.substring(file.indexOf(village) + village.length());
            }
            String realFile = lookup.get(file);
            if (realFile != null) {
                loadChestLoot(event, realFile);
            }
        }

    }

    private static void loadVillagerLoot(LootTableLoadEvent event, String tableId) {
    }

    private static void loadChestLoot(LootTableLoadEvent event, String tableId) {
        try {
            event.getTable().addPool(LootUtil.getInjectPool(MobDoctoring.MOD_ID, tableId));
            System.out.println("Loaded chest loot");
        } catch (JsonParseException e) {
            System.err.println("Could not parse loot table: " + tableId);
        }
    }
}
