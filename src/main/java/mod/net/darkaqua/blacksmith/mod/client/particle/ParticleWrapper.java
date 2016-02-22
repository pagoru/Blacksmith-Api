package net.darkaqua.blacksmith.mod.client.particle;

import net.darkaqua.blacksmith.api.client.particle.IParticle;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

/**
 * Created by cout970 on 18/01/2016.
 */
public class ParticleWrapper implements IParticle {

    private EnumParticleTypes type;
    private ItemStack item;
    private IBlockState block;

    public ParticleWrapper(EnumParticleTypes type) {
        this.type = type;
    }

    public ParticleWrapper(ItemStack item) {
        type = EnumParticleTypes.ITEM_CRACK;
        this.item = item;
    }

    public ParticleWrapper(IBlockState block, boolean dust) {
        this.block = block;
        type = dust ? EnumParticleTypes.BLOCK_DUST : EnumParticleTypes.BLOCK_CRACK;
    }

    public EnumParticleTypes getType() {
        return type;
    }

    @Override
    public String getName() {
        return type.name();
    }

    public void spawn(World w, Vect3d pos, Vect3d motion) {
        EffectRenderer eff = Minecraft.getMinecraft().effectRenderer;
        EntityFX particle;
        if (item != null) {
            particle = eff.spawnEffectParticle(type.getParticleID(), pos.getX(), pos.getY(), pos.getZ(), motion.getX(), motion.getY(), motion.getZ(), Item.getIdFromItem(item.getItem()), item.getItemDamage());
        } else if (block != null) {
            particle = eff.spawnEffectParticle(type.getParticleID(), pos.getX(), pos.getY(), pos.getZ(), motion.getX(), motion.getY(), motion.getZ(), Block.getStateId(block));
        } else {
            particle = eff.spawnEffectParticle(type.getParticleID(), pos.getX(), pos.getY(), pos.getZ(), motion.getX(), motion.getY(), motion.getZ());
        }
        particle.multipleParticleScaleBy(0.5f);
    }
}
