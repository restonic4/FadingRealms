package me.restonic4.fading_realms.advancement;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class ModAdvancementTrigger extends SimpleCriterionTrigger<ModAdvancementTrigger.Instance> {
    public final ResourceLocation resourceLocation;

    public ModAdvancementTrigger(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public ModAdvancementTrigger.Instance createInstance(JsonObject jo, ContextAwarePredicate cap, DeserializationContext dc) {
        return new ModAdvancementTrigger.Instance(cap, resourceLocation);
    }

    public void trigger(ServerPlayer serverPlayer) {
        this.trigger(serverPlayer, (thingy) -> {
            return true;
        });
    }

    @Override
    public ResourceLocation getId() {
        return resourceLocation;
    }


    public static class Instance extends AbstractCriterionTriggerInstance {

        public Instance(ContextAwarePredicate cap, ResourceLocation res) {
            super(res, cap);
        }

        public static ConstructBeaconTrigger.TriggerInstance forLevel(MinMaxBounds.Ints mmbi) {
            return new ConstructBeaconTrigger.TriggerInstance(ContextAwarePredicate.ANY, mmbi);
        }



        public JsonObject serializeToJson(SerializationContext sc) {
            JsonObject jo = super.serializeToJson(sc);
            return jo;
        }
    }
}