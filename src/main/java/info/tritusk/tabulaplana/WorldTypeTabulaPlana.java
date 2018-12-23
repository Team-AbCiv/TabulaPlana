package info.tritusk.tabulaplana;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateFlatWorld;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorFlat;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public final class WorldTypeTabulaPlana extends WorldType {

	WorldTypeTabulaPlana() {
		super(TabulaPlana.MOD_ID);
	}
	
	@Override
	public float getCloudHeight() {
		return (float) TabulaPlana.heightCloud;
	}

	@Override
	public double getHorizon(World world) {
		return TabulaPlana.heightHorizon;
	}

	@Override
	public BiomeProvider getBiomeProvider(World world) {
		return new BiomeProvider(world.getWorldInfo());
	}

	@Override
	public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
		return new ChunkGeneratorFlat(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), mask(generatorOptions));
	}

	@Override
	public int getSpawnFuzz(WorldServer world, MinecraftServer server) {
		return 0;
	}

	@Override
	public boolean isCustomizable() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void onCustomizeButton(Minecraft mc, GuiCreateWorld guiCreateWorld) {
		mc.displayGuiScreen(new GuiCreateFlatWorld(guiCreateWorld, TabulaPlana.genCode));
	}

	private static String mask(@Nullable String originalOptions) {
		if (originalOptions == null || originalOptions.isEmpty()) {
			return TabulaPlana.genCode;
		} else {
			return originalOptions;
		}
	}
}
