package info.tritusk.tabulaplana;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.Random;

/**
 * A generator that can generate bonus chest and four torches if enabled.
 * This does not perform much sanity check - it will overlap existed contents
 * at target place.
 */
final class WorldGenBonusChestLike extends WorldGenerator {
    @Override
    public boolean generate(World world, Random rand, BlockPos center) {
        if (world.isAirBlock(center)) {
            this.setBlockAndNotifyAdequately(world, center, Blocks.CHEST.getDefaultState());
            TileEntity chest = world.getTileEntity(center);
            if (chest instanceof TileEntityChest) {
                ((TileEntityChest)chest).setLootTable(LootTableList.CHESTS_SPAWN_BONUS_CHEST, world.getSeed());
            }
            for (EnumFacing direction : EnumFacing.HORIZONTALS) {
                this.setBlockAndNotifyAdequately(world, center.offset(direction), Blocks.TORCH.getDefaultState());
            }
            return true;
        } else {
            return false;
        }
    }
}
