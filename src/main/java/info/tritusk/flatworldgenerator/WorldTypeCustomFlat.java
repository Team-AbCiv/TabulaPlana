package info.tritusk.flatworldgenerator;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;

public class WorldTypeCustomFlat extends WorldType {
	
	private final String generator;
	private final boolean structure;
	private double horizon = 63.0D;
	private float cloudHeight = 128.0F;

	//Unsafe on generatorCode. Need to workaround
	public WorldTypeCustomFlat(String customname, String generator, boolean structure) {
		super("CustomFlatWorld" + customname);
		this.generator = generator;
		this.structure = structure;
	}
	
	public WorldTypeCustomFlat setCloudHeight(float newHeight) {
		this.cloudHeight = newHeight;
		return this;
	}
	
	public WorldTypeCustomFlat setHorizon(double newHorizon) {
		this.horizon = newHorizon;
		return this;
	}
	
	@Override
	public float getCloudHeight() {
		return this.cloudHeight;
	}

	@Override
	public double getHorizon(World world) {
		return this.horizon;
	}
	
	@Override
	public IChunkProvider getChunkGenerator(World world, String generatorOptions) {
		return new ChunkProviderFlat(world, world.getSeed(), this.structure, this.generator);
	}
		

}
