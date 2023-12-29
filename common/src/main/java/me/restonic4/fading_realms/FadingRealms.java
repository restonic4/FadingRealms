package me.restonic4.fading_realms;

import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import me.restonic4.fading_realms.advancement.AdvancementsManager;
import me.restonic4.fading_realms.command.CommandManager;
import me.restonic4.fading_realms.data.PlayerWorldPositionManager;
import me.restonic4.fading_realms.dimension.EndDisable;
import me.restonic4.fading_realms.dimension.Limbo;
import me.restonic4.fading_realms.entity.DivinityPortal.DivinityPortalModel;
import me.restonic4.fading_realms.entity.DivinityPortal.DivinityPortalRenderer;
import me.restonic4.fading_realms.entity.DivinityPortalInit.DivinityPortalInitModel;
import me.restonic4.fading_realms.entity.DivinityPortalInit.DivinityPortalInitRenderer;
import me.restonic4.fading_realms.item.ItemManager;
import me.restonic4.fading_realms.entity.Divinity.DivinityModel;
import me.restonic4.fading_realms.entity.Divinity.DivinityRenderer;
import me.restonic4.fading_realms.entity.EntityManager;
import me.restonic4.fading_realms.sound.SoundsRegistry;
import me.restonic4.fading_realms.tweak.TweakManager;
import me.restonic4.fading_realms.util.Camera.PacketManager;
import me.restonic4.fading_realms.util.InvisibleItemFrames;
import me.restonic4.fading_realms.util.POIManager;
import me.restonic4.restapi.RestApi;
import me.restonic4.restapi.item.ItemRegistry;
import me.restonic4.restapi.sound.SoundRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class FadingRealms
{
	public static final String MOD_ID = "fading_realms";
	public static final int RING_SIZE = 50;
	public static final float DIVINITY_SCALE = 20;

	public static void init() {
		RestApi.Log("///////////////////////////////////////////////////////", MOD_ID);
		RestApi.Log("//// - - - - |STARTING FADING REALMS CORE| - - - - ////", MOD_ID);
		RestApi.Log("///////////////////////////////////////////////////////", MOD_ID);

		//// REST API ////

		ItemRegistry.CreateRegistry(MOD_ID);
		SoundRegistry.CreateRegistry(MOD_ID);

		//// MOD ////

		InvisibleItemFrames.register();
		PlayerWorldPositionManager.register();
		EndDisable.init();
		TweakManager.init();
		POIManager.register();
		Limbo.init();
		AdvancementsManager.init();
		CommandManager.register();
		ItemManager.register();
		EntityManager.init();
		SoundsRegistry.register();
		PacketManager.init();

		//// CLIENT ////

		EnvExecutor.runInEnv(Env.CLIENT, () -> FadingRealms.Client::initializeClient);

		RestApi.Log("///////////////////////////////////////////////////////", MOD_ID);
		RestApi.Log("////  - - - - |FADING REALMS CORE LOADED| - - - -  ////", MOD_ID);
		RestApi.Log("///////////////////////////////////////////////////////", MOD_ID);

		//// EVENTS ////

		PlayerEvent.PLAYER_JOIN.register(
				(serverPlayer) -> {
					if (CommandManager.waiting) {
						PacketManager.openWaitingScreen(serverPlayer);
					}
				}
		);
	}

	@Environment(EnvType.CLIENT)
	public static class Client {
		@Environment(EnvType.CLIENT)
		public static void initializeClient() {
			EntityRendererRegistry.register(EntityManager.DIVINITY, (context) -> new DivinityRenderer(context, DIVINITY_SCALE));
			EntityModelLayerRegistry.register(DivinityModel.LAYER_LOCATION, DivinityModel::createBodyLayer);

			EntityRendererRegistry.register(EntityManager.DIVINITY_PORTAL, (context) -> new DivinityPortalRenderer(context, DIVINITY_SCALE));
			EntityModelLayerRegistry.register(DivinityPortalModel.LAYER_LOCATION, DivinityPortalModel::createBodyLayer);

			EntityRendererRegistry.register(EntityManager.DIVINITY_PORTAL_INIT, (context) -> new DivinityPortalInitRenderer(context, DIVINITY_SCALE));
			EntityModelLayerRegistry.register(DivinityPortalInitModel.LAYER_LOCATION, DivinityPortalInitModel::createBodyLayer);
		}
	}
}
