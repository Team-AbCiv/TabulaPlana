package info.tritusk.flatworldgenerator;

import java.io.File;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "CustomFlatWorldGeneator", name = "Custom Flat World Generator", version = "R1.0", useMetadata = true)
public class CustomFlatWorldGenerator {

	@Instance("CustomFlatWorldGenerator")
	public static CustomFlatWorldGenerator ins;
	
	public static File configFolder;
	
	public static Logger log;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ins = this;
		log = event.getModLog();
		
		configFolder = new File(event.getModConfigurationDirectory(), "CustomFlatWorldGenerator");
		
		if (!configFolder.exists())
			configFolder.mkdir();
		
		File mainConfig = new File(configFolder, "CustomFlatWorldGenerator.cfg");
		ConfigResolver.initMainConfig(mainConfig);
		
		File customFlatWorlds = new File(configFolder, "custom");
		
		if (!customFlatWorlds.isDirectory()) {
			customFlatWorlds.mkdir();
			log.info("Custom flat world config folder not found, initialize an empty one instead.");
			log.info("CustomFlatWorldGenerator won't continue loading this time.");
			return;
		}
		
		if (!ConfigResolver.xmlReaderInitialized)
			ConfigResolver.initXMLReader();
		
		File[] customConfigMain = customFlatWorlds.listFiles();
		
		if (customConfigMain.length < 1) {
			log.info("No files found, loading won't continue.");
			return;
		}
		
		for (File configFlatWorld : customConfigMain) {
			ConfigResolver.loadCustomFlatWorldConfig(configFlatWorld);
		}
	}
}
