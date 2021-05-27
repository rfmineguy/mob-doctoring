package com.fischerworks.mobdoctoring.events;

import com.fischerworks.mobdoctoring.init.ItemInit;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TradesEvent {

    @SubscribeEvent
    public static void villagerTradeEvent(VillagerTradesEvent event) {
        if (event.getType().equals(VillagerProfession.TOOLSMITH)) {
            System.out.println("Add trade to toolsmith villager");
            System.out.println("Level 1 trade # : " + event.getTrades().get(1).size());
            System.out.println("Level 2 trade # : " + event.getTrades().get(2).size());
            System.out.println("Level 3 trade # : " + event.getTrades().get(3).size());
            System.out.println("Level 4 trade # : " + event.getTrades().get(4).size());
            event.getTrades().get(1).add(new BasicTrade(new ItemStack(Items.EMERALD, 30), new ItemStack(ItemInit.DANGEROUS_HANDLE.get(), 1), 1, 10, 0));
        }
    }

    //make wanderers trade different things from toolsmiths
    @SubscribeEvent
    public static void wandererTradeEvent(WandererTradesEvent event) {
        event.getRareTrades().add(new BasicTrade(new ItemStack(Items.EMERALD, 30), new ItemStack(ItemInit.DANGEROUS_HANDLE.get(), 1), 1, 10, 0));
    }
}
