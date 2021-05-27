package com.fischerworks.mobdoctoring.potions.side_effects;

import com.fischerworks.mobdoctoring.init.EffectInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class SlowDeathEffect extends SideEffect {
    public SlowDeathEffect() {
        super(EffectType.HARMFUL, 0xffffff);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (this == EffectInit.SLOW_CERTAIN_DEATH.get() && entityLivingBaseIn instanceof PlayerEntity) {
            ((PlayerEntity) entityLivingBaseIn).attackEntityFrom(DamageSource.MAGIC, 1.0f);
            int foodLevel = ((PlayerEntity) entityLivingBaseIn).getFoodStats().getFoodLevel();
            if (foodLevel - 10 < 0) return;
            ((PlayerEntity) entityLivingBaseIn).getFoodStats().setFoodLevel(foodLevel - 10);
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        if (this == EffectInit.SLOW_CERTAIN_DEATH.get()) {
            int j = 120 >> amplifier; //higher number = slower
            if (j > 0) {
                return duration % j == 0;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isInstant() {
        return false;
    }
}
