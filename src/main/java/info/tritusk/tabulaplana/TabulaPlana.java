package info.tritusk.tabulaplana;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = "tabulaplana", name = "Tabula Plana", version = "R1.0", useMetadata = true)
public class TabulaPlana {

	@Mod.Instance("tabulaplana")
	public static TabulaPlana ins;
	
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
		
		new WorldTypeTabulaPlana(genCode, enableStructure).setCloudHeight(heightCloud).setHorizon(heightHorizon);
		event.getModLog().trace("Tabula Plana has successfully loaded.");
		
		if (config.hasChanged())
			config.save();
	}
}
