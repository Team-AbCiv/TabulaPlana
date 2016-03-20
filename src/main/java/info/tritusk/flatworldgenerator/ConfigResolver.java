package info.tritusk.flatworldgenerator;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public final class ConfigResolver {
	
	//No need to make it able to be initialized
	private ConfigResolver() {}
	
	public static boolean useGlobalSettings;
	
	public static int globalHorizon;
	public static int globalCloudHeight;
	
	public static void initMainConfig(File file) {
		Configuration config = new Configuration(file);
		
		useGlobalSettings = config.getBoolean("useGlobalSettings", "Main", false, "Use unified, global settings to replace all world-specified settings.");
		
		globalHorizon = config.get("GlobalSettings", "globalHorizon", 63).getInt();
		globalCloudHeight = config.get("GlobalSettings", "globalCloudHeight", 255).getInt();
	}
	
	
	public static boolean xmlReaderInitialized = false;
	
	public static void initXMLReader() {
		
	}

	public static void loadCustomFlatWorldConfig(File configFlatWorld) {
		
	}

}
