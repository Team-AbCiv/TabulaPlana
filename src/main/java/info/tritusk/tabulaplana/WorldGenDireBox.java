package info.tritusk.tabulaplana;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * A generator that can generate a 9x9 hollow shelter made of cobblestones
 * (i.e. "dire-box").
 */
final class WorldGenDireBox extends WorldGenerator {
    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        BlockPos.MutableBlockPos posPtr = new BlockPos.MutableBlockPos();
        final int shelterY = TabulaPlana.shelterY;
        final int shelterYRoof = shelterY + 6;
        for (int x = -5; x < 6; x++) {
            for (int z = -5; z < 6; z++) {
                posPtr.setPos(pos.getX() + x, shelterY, pos.getZ() + z);
                this.setBlockAndNotifyAdequately(world, posPtr, Blocks.COBBLESTONE.getDefaultState());
                final int height = shelterYRoof - shelterY;
                IBlockState stateUsed = Math.abs(x) == 5 || Math.abs(z) == 5 ? Blocks.COBBLESTONE.getDefaultState() : Blocks.AIR.getDefaultState();
                for (int yOffset = 1; yOffset < height; yOffset++) {
                    posPtr.setY(shelterY + yOffset);
                    this.setBlockAndNotifyAdequately(world, posPtr, stateUsed);
                }
                posPtr.setY(shelterYRoof);
                this.setBlockAndNotifyAdequately(world, posPtr, Blocks.COBBLESTONE.getDefaultState());
            }
        }
        return true;
    }
}
