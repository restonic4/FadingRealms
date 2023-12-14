package me.restonic4.fading_realms;

import me.restonic4.fading_realms.advancement.AdvancementsManager;
import me.restonic4.fading_realms.command.CommandManager;
import me.restonic4.fading_realms.data.PlayerWorldPositionManager;
import me.restonic4.fading_realms.dimension.EndDisable;
import me.restonic4.fading_realms.dimension.Limbo;
import me.restonic4.fading_realms.item.ItemManager;
import me.restonic4.fading_realms.tweak.TweakManager;
import me.restonic4.fading_realms.util.InvisibleItemFrames;
import me.restonic4.fading_realms.util.POIManager;
import me.restonic4.restapi.RestApi;
import me.restonic4.restapi.item.ItemRegistry;

public class FadingRealms
{
	public static final String MOD_ID = "fading_realms";
	public static final int RING_SIZE = 50;

	public static void init() {
		RestApi.Log("///////////////////////////////////////////////////////", MOD_ID);
		RestApi.Log("//// - - - - |STARTING FADING REALMS CORE| - - - - ////", MOD_ID);
		RestApi.Log("///////////////////////////////////////////////////////", MOD_ID);

		//// REST API ////

		ItemRegistry.CreateRegistry(MOD_ID);

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

		RestApi.Log("///////////////////////////////////////////////////////", MOD_ID);
		RestApi.Log("////  - - - - |FADING REALMS CORE LOADED| - - - -  ////", MOD_ID);
		RestApi.Log("///////////////////////////////////////////////////////", MOD_ID);
	}
}
