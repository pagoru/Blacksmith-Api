package net.darkaqua.blacksmith.api.block;

/**
 * Created by cout970 on 28/12/2015.
 */
public interface IBlockMaterial {

    boolean isLiquid();

    boolean isSolid();

    boolean blocksLight();

    boolean blocksMovement();

    boolean canBurn();

    boolean isReplaceable();

    boolean isOpaque();

    boolean isToolNotRequired();

    Object getInternalMaterial();
}
