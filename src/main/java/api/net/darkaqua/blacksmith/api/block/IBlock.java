package net.darkaqua.blacksmith.api.block;

import com.google.common.base.Predicate;
import net.darkaqua.blacksmith.api.block.methods.BlockMethod;
import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * This is an abstraction to a Minecraft block
 * This must not be implemented
 *
 * @author cout970
 */
public interface IBlock extends BlockMethod.AllBlockMethods {

    /**
     * @return The internal block name in the form: tile.blockname.name
     */
    String getUnlocalizedName();

    /**
     * @return The localized name of the block
     */
    String getLocalizedName();

    /**
     * This attribute defines the default size, collision box and selection box of the block
     * The collision box and the selection box can be changes in dynamically in other methods
     *
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
     *
     * @return The hardness of the block
     */
    float getHardness();

    /**
     * This attribute defines the amount of light that the block will emit,
     * the value must be between 0F and 1F
     *
     * @return the amount of light emitted by the block
     */
    float getLightEmitted();

    /**
     * This attribute defines the amount of light that the block will absorb,
     * the value must be between 0F and 1F
     *
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
     *
     * @return the resistance of the block to explosions
     */
    float getResistance();

    /**
     * The item that represents this block on an inventory
     */
    IItem getItemBlock();

    /**
     * If the block was created by an IBlockDefinition this method will return this definition, otherwise will return null
     */
    IBlockDefinition getBlockDefinition();

    boolean isOpaque();

    IBlockVariant getDefaultVariant();

    IBlockVariant getVariantFromMeta(int meta);

    int getMetaFromVariant(IBlockVariant variant);

    ICreativeTab getCreativeTab();

    boolean canBeReplacedByOreGen(WorldRef ref, final Predicate<IBlockVariant> target);

    /**
     * The internal minecraft block
     * Useful to check interfaces and apis outside Blacksmith
     */
    Object getInternalBlock();
}
