package me.restonic4.fading_realms.entity;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import me.restonic4.fading_realms.entity.Divinity.Divinity;
import me.restonic4.fading_realms.entity.DivinityPortal.DivinityPortal;
import me.restonic4.fading_realms.entity.DivinityPortalInit.DivinityPortalInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import static me.restonic4.fading_realms.FadingRealms.DIVINITY_SCALE;
import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

public class EntityManager {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<Divinity>> DIVINITY = ENTITY_TYPES.register(
            "divinity",
            () -> EntityType.Builder.of(Divinity::new, MobCategory.CREATURE)
                    .sized(0.6f * DIVINITY_SCALE,1.8f * DIVINITY_SCALE)
                    .fireImmune()
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(MOD_ID, "divinity").toString())
    );

    public static final RegistrySupplier<EntityType<DivinityPortal>> DIVINITY_PORTAL = ENTITY_TYPES.register(
            "divinity_portal",
            () -> EntityType.Builder.of(DivinityPortal::new, MobCategory.CREATURE)
                    .sized(0.6f * DIVINITY_SCALE,1.8f * DIVINITY_SCALE)
                    .fireImmune()
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(MOD_ID, "divinity_portal").toString())
    );

    public static final RegistrySupplier<EntityType<DivinityPortalInit>> DIVINITY_PORTAL_INIT = ENTITY_TYPES.register(
            "divinity_portal_init",
            () -> EntityType.Builder.of(DivinityPortalInit::new, MobCategory.CREATURE)
                    .sized(0.6f * DIVINITY_SCALE,1.8f * DIVINITY_SCALE)
                    .fireImmune()
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(MOD_ID, "divinity_portal_init").toString())
    );

    public static void init() {
        ENTITY_TYPES.register();

        EntityAttributeRegistry.register(DIVINITY, Divinity::createAttributes);
        EntityAttributeRegistry.register(DIVINITY_PORTAL, DivinityPortal::createAttributes);
        EntityAttributeRegistry.register(DIVINITY_PORTAL_INIT, DivinityPortalInit::createAttributes);
    }
}
