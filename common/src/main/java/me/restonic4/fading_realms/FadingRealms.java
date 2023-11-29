package me.restonic4.fading_realms;

import me.restonic4.fading_realms.util.InvisibleItemFrames;
import me.restonic4.restapi.RestApi;

public class FadingRealms
{
	public static final String MOD_ID = "fading_realms";

	public static void init() {
		RestApi.Log("///////////////////////////////////////////////////////", MOD_ID);
		RestApi.Log("//// - - - - |STARTING FADING REALMS CORE| - - - - ////", MOD_ID);
		RestApi.Log("///////////////////////////////////////////////////////", MOD_ID);

		InvisibleItemFrames.register();
	}
}
