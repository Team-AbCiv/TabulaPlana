package info.tritusk.tabulaplana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;

public class WorldTypeTabulaPlana extends WorldType {
	
	private final String generator;
	private final boolean structure;
	private double horizon = 63.0D;
	private float cloudHeight = 128.0F;

	//Unsafe on generatorCode. Need to workaround
	public WorldTypeTabulaPlana(String generator, boolean structure) {
		super("tabula_plana");
		this.generator = generator;
		this.structure = structure;
	}
	
	public WorldTypeTabulaPlana setCloudHeight(float newHeight) {
		this.cloudHeight = newHeight;
		return this;
	}
	
	public WorldTypeTabulaPlana setHorizon(double newHorizon) {
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
	
	@SideOnly(Side.CLIENT)
	public String getTranslateName() {
		if (this.getWorldTypeName().startsWith("DummyWorld"))
			return "generator.DummyWorld";
		else return super.getTranslateName();
	}
	
	@SideOnly(Side.CLIENT)
	public String func_151359_c() {
		if (this.getWorldTypeName().startsWith("DummyWorld"))
			return "generator.DummyWorld.info";
		else return super.func_151359_c();
	}
		

}
