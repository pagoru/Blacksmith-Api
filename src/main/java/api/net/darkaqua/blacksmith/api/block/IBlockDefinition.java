package net.darkaqua.blacksmith.api.block;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.block.blockdata.BlockDataFactory;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockDataGenerator;
import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.inventory.ItemStackFactory;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.api.util.annotations.Implementable;

import java.util.List;

/**
 * This interface is mended to be implemented by the modders
 * This interface defines a new block
 * For a default implementation of this interface, see DefaultBlockDefinition
 * <p>
 * Created by cout970 on 08/11/2015.
 */
@Implementable
public interface IBlockDefinition {

    /**
     * This method is called when the block is created
     * allowing to know the parent block to be returned by getBlock()
     */
    void onCreate(IBlock block);

    /**
     * Returns the Block that this definition has created
     *
     * @return the parent block of this definition given by the method onCreate(IBlock block)
     */
    IBlock getBlock();

    /**
     * This method specifies the block Material
     */
    IBlockMaterial getBlockMaterial();

    // BlockData {

    /**
     * Creates a new IBlockDataGenerator that will create the default IBlockData
     * using the block and an array of attributes
     */
    default IBlockDataGenerator getBlockDataGenerator() {
        return BlockDataFactory.createBlockDataGenerator(getBlock());
    }

    /**
     * This method allow to define the default values of the default IBlockData
     *
     * @param iBlockData default block data
     * @return the final block data
     */
    default IBlockData onCreateDefaultBlockData(IBlockData iBlockData) {
        return iBlockData;
    }

    // This two method allow to translate the metadata from the saves to an IBlockData and vice versa

    default IBlockData translateMetadataToVariant(int meta) {
        return getBlock().getDefaultBlockData();
    }

    default int translateVariantToMetadata(IBlockData variant) {
        return 0;
    }

    // BlockData }

    /**
     * The internal name of the block
     * Internally "tile." is appended to the name
     *
     * @return the name of the block
     */
    String getUnlocalizedName();

    /**
     * This method gets the selection cube used by the player to detect the block in world
     *
     * @param ref the block reference
     * @return the selection hitbox
     */
    default Cube getSelectionCube(WorldRef ref) {
        return Cube.fullBlock();
    }

    /**
     * This method gets the collision cubes used to collide entities with the block
     *
     * @param ref    the block reference
     * @param entity the entity colliding
     * @return the collision cubes
     */
    default List<Cube> getCollisionCubes(WorldRef ref, IEntity entity) {
        return Lists.newArrayList(Cube.fullBlock());
    }

    /**
     * This attribute defines how hard is the block to mine.
     * if the hardness is negative the block will be unbreakable
     * Some examples:
     * Stone: 1.5F
     * Dirt: 0.5F
     * Obsidian: 50F
     * Bedrock: -1F
     *
     * @return The hardness of the block
     */
    default float getHardness(WorldRef ref) {
        return 1.5F;
    }

    /**
     * This attribute defines the amount of light that the block will emit,
     * the value must be between 0F and 1F
     *
     * @return the amount of light emitted by the block
     */
    default float getLightEmitted() {
        return 0F;
    }

    /**
     * This attribute defines the amount of light that the block will absorb,
     * the value must be between 0F and 1F
     *
     * @return the amount of light absorbed by the block
     */
    default float getLightOpacity() {
        return 1F;
    }

    /**
     * This attribute defines the resistance to explosions of the block
     * Some examples:
     * Stone: 10F
     * Dirt: 2.5F
     * Obsidian: 2000F
     * Bedrock: 6000000F
     * Maybe in the future a Explosion argument will be added.
     *
     * @param exploder the entity that creates the explosion, can be null
     * @return the resistance of the block to explosions
     */
    default float getExplosionResistance(WorldRef ref, IEntity exploder) {
        return 10F;
    }

    /**
     * @return the creative tab where this block will appear
     */
    ICreativeTab getCreativeTab();

    /**
     * Used to render TileEntities, allow to disable the block render
     */
    default boolean shouldRender() {
        return true;
    }

    /**
     * This method check is this block is a opaque, full, and non transparent cube
     * if the return is true then the adjacent block will not render the side that
     * is hidden by this block
     */
    default boolean isFullCube() {
        return true;
    }

    /**
     * This method gathers the items that the creative tab should display
     *
     * @param item        the itemBlock
     * @param creativeTab the creative tab
     * @param list        the items that the creative tab will display
     */
    default void getSubBlocks(IItem item, ICreativeTab creativeTab, List<IItemStack> list) {
        list.add(ItemStackFactory.createItemStack(item));
    }
}
