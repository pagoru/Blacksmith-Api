package net.darkaqua.blacksmith.mod.block;

import net.darkaqua.blacksmith.api.block.IBlockMaterial;
import net.minecraft.block.material.Material;

/**
 * Created by cout970 on 28/12/2015.
 */
public class MaterialWrapper implements IBlockMaterial {

    private Material material;

    public MaterialWrapper(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public boolean isLiquid() {
        return material.isLiquid();
    }

    @Override
    public boolean isSolid() {
        return material.isSolid();
    }

    @Override
    public boolean blocksLight() {
        return material.blocksLight();
    }

    @Override
    public boolean blocksMovement() {
        return material.blocksMovement();
    }

    @Override
    public boolean canBurn() {
        return material.getCanBurn();
    }

    @Override
    public boolean isReplaceable() {
        return material.isReplaceable();
    }

    @Override
    public boolean isOpaque() {
        return material.isOpaque();
    }

    @Override
    public boolean isToolNotRequired() {
        return material.isToolNotRequired();
    }

    @Override
    public Object getInternalMaterial() {
        return material;
    }
}
