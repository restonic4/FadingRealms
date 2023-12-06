package me.restonic4.fading_realms;

import me.restonic4.fading_realms.data.PlayerWorldPositionManager;
import me.restonic4.fading_realms.dimension.EndDisable;
import me.restonic4.fading_realms.tweak.TweakManager;
import me.restonic4.fading_realms.util.InvisibleItemFrames;
import me.restonic4.fading_realms.util.POIManager;
import me.restonic4.restapi.RestApi;

public class FadingRealms
{
	public static final String MOD_ID = "fading_realms";

	public static void init() {
		RestApi.Log("///////////////////////////////////////////////////////", MOD_ID);
		RestApi.Log("//// - - - - |STARTING FADING REALMS CORE| - - - - ////", MOD_ID);
		RestApi.Log("///////////////////////////////////////////////////////", MOD_ID);

		InvisibleItemFrames.register();
		PlayerWorldPositionManager.register();
		EndDisable.init();
		TweakManager.init();
		POIManager.register();

		RestApi.Log("///////////////////////////////////////////////////////", MOD_ID);
		RestApi.Log("////  - - - - |FADING REALMS CORE LOADED| - - - -  ////", MOD_ID);
		RestApi.Log("///////////////////////////////////////////////////////", MOD_ID);
	}
}
