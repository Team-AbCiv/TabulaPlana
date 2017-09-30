package info.tritusk.tabulaplana;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorFlat;

public class WorldTypeTabulaPlana extends WorldType {
	
	private final String generator;
	private final boolean structure;
	private double horizon = 63.0D;
	private float cloudHeight = 128.0F;

	WorldTypeTabulaPlana(String generator, boolean structure) {
		super("tabula_plana");
		this.generator = generator;
		this.structure = structure;
	}
	
	WorldTypeTabulaPlana setCloudHeight(float newHeight) {
		this.cloudHeight = newHeight;
		return this;
	}
	
	WorldTypeTabulaPlana setHorizon(double newHorizon) {
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
	public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
		return new ChunkGeneratorFlat(world, world.getSeed(), this.structure, this.generator);
	}

}
