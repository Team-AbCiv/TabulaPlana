package info.tritusk.tabulaplana;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Config(modid = TabulaPlana.MOD_ID, name = "TabulaPlana")
@Mod(modid = TabulaPlana.MOD_ID, name = "Tabula Plana", version = "@VER@", useMetadata = true)
@Mod.EventBusSubscriber(modid = TabulaPlana.MOD_ID)
public final class TabulaPlana {

	static final String MOD_ID = "tabula_plana";

	@Config.Comment("Superflat customization code.")
	@Config.RequiresMcRestart
	public static String genCode = "3;minecraft:bedrock,2*minecraft:dirt,minecraft:grass;1;village";
	@Config.Comment("Height of horizon")
	@Config.RangeDouble(min = 0, max = 255)
	public static double heightHorizon = 63;
	@Config.Comment("Height of cloud")
	@Config.RangeInt(min = 0, max = 255)
	public static double heightCloud = 128;

	@Config.Comment("Height of center of shelter, used to for shelter generation")
	@Config.RangeInt(min = 1, max = 255)
	@Config.RequiresMcRestart
	public static int shelterY = 63;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		new WorldTypeTabulaPlana();
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void handleWorldInitializing(WorldEvent.CreateSpawnPosition event) {
		World world = event.getWorld();
		if (world.getWorldType() instanceof WorldTypeTabulaPlana) {
			event.setCanceled(true);
			BlockPos spawnPoint = new BlockPos(world.rand.nextInt(256), TabulaPlana.shelterY + 1, world.rand.nextInt(256));
			event.getWorld().setSpawnPoint(spawnPoint);
			new WorldGenDireBox().generate(world, world.rand, spawnPoint);
			if (event.getSettings().isBonusChestEnabled()) {
				new WorldGenBonusChestLike().generate(world, world.rand, spawnPoint);
			}
		}
	}

	@Mod.EventBusSubscriber(Side.CLIENT)
	public static final class ConfigSyncer {
		@SubscribeEvent
		public static void syncConfig(ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(TabulaPlana.MOD_ID)) {
				ConfigManager.sync(TabulaPlana.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}

}
