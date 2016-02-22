package net.darkaqua.blacksmith.api.client.registry;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.client.particle.IParticle;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.world.IWorld;

/**
 * Created by cout970 on 18/01/2016.
 */
public interface IParticleManager {

    void addParticle(IWorld world, IParticle particle, Vect3d pos, Vect3d motion);

    IParticle getParticle(String name);
    IParticle getItemCrackParticle(IItemStack item);
    IParticle getBlockCrackParticle(IBlockData block);
    IParticle getBlockDustParticle(IBlockData block);
}
