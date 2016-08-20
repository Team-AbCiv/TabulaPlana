package info.tritusk.tabulaplana;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = "tabulaplana", name = "Tabula Plana", version = "R1.0", useMetadata = true)
public class TabulaPlana {

	@Mod.Instance("tabulaplana")
	public static TabulaPlana ins;
	
	public static Logger log;
	
	public static boolean enableStructure;
	
	public static String genCode;
	
	public static int globalHorizon;
	public static int globalCloudHeight;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ins = this;
		log = event.getModLog();
		
		final Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		
		enableStructure = config.get("GlobalSettings", "enableStructure", true).getBoolean();
		genCode = config.get("GlobalSettings", "genCode", "").getString();
		globalHorizon = config.get("GlobalSettings", "globalHorizon", 63).getInt();
		globalCloudHeight = config.get("GlobalSettings", "globalCloudHeight", 255).getInt();
		
		new WorldTypeTabulaPlana(genCode, enableStructure).setCloudHeight(globalCloudHeight).setHorizon(globalHorizon);
		
		if (config.hasChanged())
			config.save();
	}
}
