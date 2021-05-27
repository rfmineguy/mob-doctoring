package com.fischerworks.mobdoctoring.items;

import com.fischerworks.mobdoctoring.util.MobDoctoringTier;
import net.minecraft.item.Item;

public class EssenseBottleItem extends Item {
    MobDoctoringTier tier;
    public EssenseBottleItem(MobDoctoringTier tierIn, Properties builder) {
        super(builder);
        this.tier = tierIn;
    }
}
