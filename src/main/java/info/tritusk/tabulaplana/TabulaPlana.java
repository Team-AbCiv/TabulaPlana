package info.tritusk.tabulaplana;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "tabulaplana", name = "Tabula Plana", version = "@VER@", useMetadata = true)
@Mod.EventBusSubscriber
public final class TabulaPlana {

	@Config(modid = "tabulaplana", name = "TabulaPlana")
	public static final class Cfg {
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

		@Config.Comment("Height of center of shelter, used to for shelter generation")
		@Config.RangeInt(min = 1, max = 255)
		public static int shelterY = 63;
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		new WorldTypeTabulaPlana(Cfg.genCode, Cfg.enableStructure).setCloudHeight(Cfg.heightCloud).setHorizon(Cfg.heightHorizon);
		if (Boolean.parseBoolean(System.getProperty("tabulaplana.debug"))) {
			event.getModLog().trace("Tabula Plana has successfully loaded.");
		}
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		World world = event.getServer().getWorld(0);
		BlockPos spawnPoint = world.getSpawnPoint();
		BlockPos.MutableBlockPos posPtr = new BlockPos.MutableBlockPos();
		final int shelterY = TabulaPlana.Cfg.shelterY;
		final int shelterYRoof = shelterY + 6;
		for (int x = -4; x < 5; x++) {
			for (int z = -4; z < 5; z++) {
				posPtr.setPos(spawnPoint.getX() + x, shelterY, spawnPoint.getZ() + z);
				world.setBlockState(posPtr, Blocks.COBBLESTONE.getDefaultState(), 22);
				final int height = shelterYRoof - shelterY;
				for (int yOffset = 1; yOffset < height; yOffset++) {
					posPtr.setY(shelterY + yOffset);
					world.setBlockState(posPtr, Blocks.AIR.getDefaultState(), 22);
				}
				posPtr.setY(shelterYRoof);
				world.setBlockState(posPtr, Blocks.COBBLESTONE.getDefaultState(), 22);
			}
		}
		world.setBlockState(spawnPoint, Blocks.TORCH.getDefaultState());
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void handleWorldInitializing(WorldEvent.CreateSpawnPosition event) {
		World world = event.getWorld();
		if (world.getWorldType() instanceof WorldTypeTabulaPlana) {
			event.setCanceled(true);
			BlockPos spawnPoint = new BlockPos(world.rand.nextInt(256), Cfg.shelterY + 1, world.rand.nextInt(256));
			event.getWorld().setSpawnPoint(spawnPoint);
		}
	}

}
