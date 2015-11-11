package net.darkaqua.blacksmith.api.block;

import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.util.Cube;

/**
 * This interface is mended to be implemented by the modders
 * This interface defines a new block
 * For a default implementation of this interface, see DefaultBlockDefinition
 *
 * Created by cout970 on 08/11/2015.
 */
public interface IBlockDefinition {

    /**
     * The internal name of the block
     * @return the name of the block
     */
    String getUnlocalizedName();

    /**
     * This attribute defines the default size, collision box and selection box of the block
     * The collision box and the selection box can be changes in dynamically in other methods
     * @return The size of the block
     */
    Cube getBlockBounds();

    /**
     * This attribute defines how hard is mine the block.
     * if the hardness is negative the block will be unbreakable
     * Some examples:
     * Stone: 1.5F
     * Dirt: 0.5F
     * Obsidian: 50F
     * Bedrock: -1F
     * @return The hardness of the block
     */
    float getBlockHardness();

    /**
     * This attribute defines the amount of light that the block will emit,
     * the value must be between 0F and 1F
     * @return the amount of light emitted by the block
     */
    float getLightEmitted();

    /**
     * This attribute defines the amount of light that the block will absorb,
     * the value must be between 0F and 1F
     * @return the amount of light absorbed by the block
     */
    float getLightOpacity();

    /**
     * This attribute defines the resistance to explosions of the block
     * Some examples:
     * Stone: 10F
     * Dirt: 2.5F
     * Obsidian: 2000F
     * Bedrock: 6000000F
     * @return the resistance of the block to explosions
     */
    float getBlockResistance();

    /**
     * @return the creative tab where this block will appear
     */
    ICreativeTab getCreativeTab();
}
