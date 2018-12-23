package info.tritusk.tabulaplana;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorFlat;

public final class WorldTypeTabulaPlana extends WorldType {
	
	private final String generator;

	WorldTypeTabulaPlana(String generator) {
		super(TabulaPlana.MOD_ID);
		this.generator = generator;
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
		return new ChunkGeneratorFlat(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), this.generator);
	}

	@Override
	public int getSpawnFuzz(WorldServer world, MinecraftServer server) {
		return 0;
	}
}
