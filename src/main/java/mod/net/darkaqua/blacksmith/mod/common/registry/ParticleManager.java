package net.darkaqua.blacksmith.mod.common.registry;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.client.registry.IParticleManager;
import net.darkaqua.blacksmith.api.client.particle.IParticle;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.world.IWorld;
import net.darkaqua.blacksmith.mod.client.particle.ParticleWrapper;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

/**
 * Created by cout970 on 18/01/2016.
 */
public class ParticleManager implements IParticleManager{

    public static final ParticleManager INSTANCE = new ParticleManager();
    private ParticleManager(){}

    @Override
    public void addParticle(IWorld world, IParticle particle, Vect3d pos, Vect3d motion) {
        World w = MCInterface.toWorld(world);
        ParticleWrapper wp = (ParticleWrapper) particle;
        wp.spawn(w, pos, motion);
    }

    @Override
    public IParticle getParticle(String name) {
        EnumParticleTypes type = null;
        for (EnumParticleTypes t : EnumParticleTypes.values()){
            if (t.getParticleName().equals(name)){
                type = t;
                break;
            }
        }
        if (type == null)return null;
        return new ParticleWrapper(type);
    }

    @Override
    public IParticle getItemCrackParticle(IItemStack item) {
        return new ParticleWrapper(MCInterface.toItemStack(item));
    }

    @Override
    public IParticle getBlockCrackParticle(IBlockData block) {
        return new ParticleWrapper(MCInterface.toBlockState(block), false);
    }

    @Override
    public IParticle getBlockDustParticle(IBlockData block) {
        return new ParticleWrapper(MCInterface.toBlockState(block), true);
    }
}
