package info.tritusk.tabulaplana;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Config;

@Mod(modid = "tabulaplana", name = "Tabula Plana", version = "@VER@", useMetadata = true)
public enum TabulaPlana {

	INSTANCE;

	@Mod.InstanceFactory
	public static TabulaPlana getInstance() {
		return INSTANCE;
	}
	@Config(modid = "tabulaplana", name = "TabulaPlana")
	public static class Cfg {
		@Config.Comment("Set to true to enable structure, e.g. village, dungeon.")
		public static boolean enableStructure = true;
		@Config.Comment("Superflat customization code.")
		public static String genCode = "3;minecraft:bedrock,2*minecraft:dirt,minecraft:grass;1;village";
		@Config.Comment("Height of horizon")
		@Config.RangeInt(min = 0, max = 255)
		public static int heightHorizon = 63;
		@Config.Comment("Height of cloud")
		@Config.RangeInt(min = 0, max = 255)
		public static int heightCloud = 128;
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		new WorldTypeTabulaPlana(Cfg.genCode, Cfg.enableStructure).setCloudHeight(Cfg.heightCloud).setHorizon(Cfg.heightHorizon);
		event.getModLog().trace("Tabula Plana has successfully loaded.");
	}
}
