package net.darkaqua.blacksmith.api.world.generation;

import com.google.common.base.Predicate;
import net.darkaqua.blacksmith.api.block.Blocks;
import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.util.Vect3i;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.api.world.IWorld;

import java.util.Random;

/**
 * Created by cout970 on 18/12/2015.
 */
public class OreGenerator {

    protected IBlockData ore;
    protected int blocksPerVein;
    protected Predicate<IBlockData> predicate;

    public OreGenerator(IBlock ore, int number) {
        this(ore.getDefaultBlockData(), number, input -> input != null && input.getBlock().equals(Blocks.STONE.getBlock())
        );
    }

    public OreGenerator(IBlockData ore, int number) {
        this(ore, number, input -> input != null && input.getBlock().equals(Blocks.STONE.getBlock())
        );
    }

    public OreGenerator(IBlockData ore, int number, Predicate<IBlockData> target) {
        this.ore = ore;
        blocksPerVein = number;
        predicate = target;
    }

    public OreGenerator(IBlock ore, int meta, int number, Predicate<IBlockData> target) {
        this(ore.getBlockDataFromMeta(meta), number, target);
    }

    public void generate(IWorld world, Random random, int x, int y, int z) {
        float angle = random.nextFloat() * (float) Math.PI;
        double posX = (x + 8) + Math.sin(angle) * blocksPerVein / 8.0F;
        double negX = (x + 8) - Math.sin(angle) * blocksPerVein / 8.0F;
        double posZ = (z + 8) + Math.cos(angle) * blocksPerVein / 8.0F;
        double negZ = (z + 8) - Math.cos(angle) * blocksPerVein / 8.0F;
        double y1 = y + random.nextInt(3) - 2;
        double y2 = y + random.nextInt(3) - 2;

        for (int n = 0; n <= blocksPerVein; ++n) {

            double xPlace = posX + (negX - posX) * n / blocksPerVein;
            double yPlace = y1 + (y2 - y1) * n / blocksPerVein;
            double zPlace = posZ + (negZ - posZ) * n / blocksPerVein;
            double scale = random.nextDouble() * blocksPerVein / 16.0D;
            double desp = (Math.sin(n * Math.PI / blocksPerVein) + 1.0F) * scale + 1.0D;

            int minX = floor_double(xPlace - desp / 2.0D);
            int minY = floor_double(yPlace - desp / 2.0D);
            int minZ = floor_double(zPlace - desp / 2.0D);
            int maxX = floor_double(xPlace + desp / 2.0D);
            int maxY = floor_double(yPlace + desp / 2.0D);
            int maxZ = floor_double(zPlace + desp / 2.0D);

            for (int i = minX; i <= maxX; ++i) {
                double xDistance = ((double) i + 0.5D - xPlace) / (desp / 2.0D);

                if (xDistance * xDistance < 1.0D) {
                    for (int j = minY; j <= maxY; ++j) {
                        double yDistance = ((double) j + 0.5D - yPlace) / (desp / 2.0D);

                        if (xDistance * xDistance + yDistance * yDistance < 1.0D) {
                            for (int k = minZ; k <= maxZ; ++k) {

                                double zDistance = ((double) k + 0.5D - zPlace) / (desp / 2.0D);
                                Vect3i blockPos = new Vect3i(i, j, k);
                                WorldRef ref = new WorldRef(world, blockPos);

                                if (xDistance * xDistance + yDistance * yDistance + zDistance * zDistance < 1.0D) {
                                    if (ref.getBlockData().getBlock().canBeReplacedByOreGen(ref, predicate)) {
                                        ref.setBlockData(ore, 2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static int floor_double(double value) {
        int i = (int) value;
        return value < (double) i ? i - 1 : i;
    }

    public IBlockData getOre() {
        return ore;
    }

    public int getBlocksPerVein() {
        return blocksPerVein;
    }

    public void setBlocksPerVein(int blocksPerVein) {
        this.blocksPerVein = blocksPerVein;
    }
}
