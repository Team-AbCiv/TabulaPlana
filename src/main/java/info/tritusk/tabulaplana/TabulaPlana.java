package info.tritusk.tabulaplana;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = "tabulaplana", name = "Tabula Plana", version = "@VER@", useMetadata = true)
public enum TabulaPlana {

	INSTANCE;

	@Mod.InstanceFactory
	public static TabulaPlana getInstance() {
		return INSTANCE;
	}
	
	public static boolean enableStructure;
	public static String genCode;
	public static int heightHorizon, heightCloud;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		final Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		enableStructure = config.get("GlobalSettings", "enableStructure", true).getBoolean();
		genCode = config.get("GlobalSettings", "genCode", "3;minecraft:air;127;decoration").getString();
		heightHorizon = config.get("GlobalSettings", "globalHorizon", 63).getInt();
		heightCloud = config.get("GlobalSettings", "globalCloudHeight", 255).getInt();
		new WorldTypeTabulaPlana();
		if (config.hasChanged())
			config.save();
	}
}
