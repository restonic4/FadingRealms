package me.restonic4.fading_realms.advancement;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class CoolAdvancement {
    public final String advancement_id;
    public final ResourceLocation advancement_location;
    public final ModAdvancementTrigger advancement;

    public CoolAdvancement(String id) {
        this.advancement_id = id;
        this.advancement_location = new ResourceLocation("fading_realms", id);
        this.advancement = new ModAdvancementTrigger(this.advancement_location);
    }

    protected void registerCriteriaTriggers() {
        CriteriaTriggers.register(advancement);
    }

    public void register() {
        registerCriteriaTriggers();
    }

    public void grant(ServerPlayer serverPlayer) {
        Advancement advancementInstance = serverPlayer.server.getAdvancements().getAdvancement(this.advancement_location);

        serverPlayer.getAdvancements().award(advancementInstance, this.advancement_id);
    }

    public Advancement getAdvancement(ServerPlayer serverPlayer) {
        return serverPlayer.server.getAdvancements().getAdvancement(this.advancement_location);
    }
}
