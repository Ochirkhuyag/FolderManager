package config;

public class FeatureFlag {
	private static boolean isPrintDebugMessage;
	
	public static boolean getPrintDebugMessage() {
		return isPrintDebugMessage;
	}

	public static void setPrintDebugMessage(boolean showDebugFlag) {
		isPrintDebugMessage = showDebugFlag;
	}
	
}
