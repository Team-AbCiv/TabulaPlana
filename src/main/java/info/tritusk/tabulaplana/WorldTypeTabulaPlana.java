package info.tritusk.tabulaplana;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderFlat;

public class WorldTypeTabulaPlana extends WorldType {

	public WorldTypeTabulaPlana() {
		super("tabula_plana");
	}
	
	@Override
	public float getCloudHeight() {
		return TabulaPlana.heightCloud;
	}

	@Override
	public double getHorizon(World world) {
		return TabulaPlana.heightHorizon;
	}

	@Override
	public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
		return new ChunkProviderFlat(world, world.getSeed(), TabulaPlana.enableStructure, TabulaPlana.genCode);
	}

}
