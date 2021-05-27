package com.fischerworks.mobdoctoring.potions;

import com.fischerworks.mobdoctoring.util.SerumType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class MobImmunityEffect extends Effect {
    private final SerumType type;

    public MobImmunityEffect(SerumType type, int liquidColorIn) {
        super(EffectType.BENEFICIAL, liquidColorIn);
        this.type = type;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    public SerumType getSerumType() {
        return type;
    }
}