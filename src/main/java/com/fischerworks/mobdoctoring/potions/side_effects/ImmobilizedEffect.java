package com.fischerworks.mobdoctoring.potions.side_effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;

public class ImmobilizedEffect extends SideEffect {
    public ImmobilizedEffect() {
        super(EffectType.HARMFUL, 0xffffff);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {

    }
}
