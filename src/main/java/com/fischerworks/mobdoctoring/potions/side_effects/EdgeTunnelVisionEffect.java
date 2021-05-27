package com.fischerworks.mobdoctoring.potions.side_effects;

import com.fischerworks.mobdoctoring.events.OverlayEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.potion.EffectType;

public class EdgeTunnelVisionEffect extends SideEffect {
    public EdgeTunnelVisionEffect() {
        super(EffectType.HARMFUL, 0xffffff);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn.world.isRemote()) {
            OverlayEvent.activeEffect = OverlayEvent.ActiveEffect.EDGE;
        }
        super.performEffect(entityLivingBaseIn, amplifier);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration > 0;
    }

    @Override
    public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
        OverlayEvent.activeEffect = OverlayEvent.ActiveEffect.NONE;
        super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    @Override
    public boolean isInstant() {
        return false;
    }
}
