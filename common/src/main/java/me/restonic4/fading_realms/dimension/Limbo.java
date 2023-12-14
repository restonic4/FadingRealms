package me.restonic4.fading_realms.dimension;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class Limbo {
    public static void init() {
        TickEvent.SERVER_POST.register(
                (server) -> {
                    for (ServerLevel levelFound : server.getAllLevels()) {
                        String dimName = levelFound.dimension().toString();

                        if (dimName.contains("before") && dimName.contains("limbo")) {
                            for (ServerPlayer serverPlayer : levelFound.players()) {
                                if (serverPlayer.getY() <= 0) {
                                    MobEffectInstance effect = new MobEffectInstance(MobEffects.LEVITATION, 60, 1);

                                    serverPlayer.addEffect(effect);
                                }
                            }
                        }
                    }
                }
        );
    }
}
