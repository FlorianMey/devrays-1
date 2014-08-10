package app.jaid.devrays.debug;

/**
 * Contains public static fields that can be set from any place in the code and polled for indicating which (debug-)
 * features are activated.
 *
 * @author jaid
 */
public class DebugFlags {

	public static boolean debugMode = false;
	public static boolean displayContextInfoIngame = false;
	public static boolean drawCoords = true;
	public static boolean drawHitboxes = false;
	public static float showCoordsAlpha = 0.5f;
	public static boolean showMobPosition = true;
}
