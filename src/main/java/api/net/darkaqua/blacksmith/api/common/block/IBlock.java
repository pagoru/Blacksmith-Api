package net.darkaqua.blacksmith.api.common.block;

import com.google.common.base.Predicate;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.block.methods.BlockMethod;
import net.darkaqua.blacksmith.api.client.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.common.entity.IEntity;
import net.darkaqua.blacksmith.api.common.item.IItem;
import net.darkaqua.blacksmith.api.common.util.*;
import net.darkaqua.blacksmith.api.common.util.raytrace.Cube;
import net.darkaqua.blacksmith.api.common.util.raytrace.RayTraceResult;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3i;
import net.darkaqua.blacksmith.api.common.world.IWorldAccess;

import java.util.List;

/**
 * This is an abstraction to a Minecraft block
 * This must not be implemented
 *
 * @author cout970
 */
public interface IBlock extends BlockMethod.AllBlockMethods {

    /**
     * Returns the internal block name in the form: tile.blockname.name
     */
    String getUnlocalizedName();

    /**
     * Returns the localized name of the block
     */
    String getLocalizedName();

    /**
     * This method gets the selection cube used by the player to detect the block in world
     *
     * @param ref the block reference
     * @return the selection hitbox
     */
    Cube getSelectionCube(WorldRef ref);

    /**
     * This method gets the collision cubes used to collide entities with the block
     *
     * @param ref    the block reference
     * @param entity the entity colliding, may be null
     * @return the collision cubes
     */
    List<Cube> getCollisionCubes(WorldRef ref, IEntity entity);

    /**
     * This method checks if the ray(start, end) collides with this block and returns
     * the point where the ray intersects with this block
     *
     * @param ref the block reference
     * @param start the start point of the ray
     * @param end the end point of the ray
     * @return the result of the ray trace
     */
    RayTraceResult rayTraceBlock(WorldRef ref, Vect3d start, Vect3d end);

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
    float getHardness(WorldRef ref);

    /**
     * This attribute defines the amount of light that the block will emit,
     * the value must be between 0F and 1F
     *
     * @return the amount of light emitted by the block
     */
    float getLightEmitted(IWorldAccess access, Vect3i pos);

    /**
     * This attribute defines the amount of light that the block will absorb,
     * the value must be between 0F and 1F
     *
     * @return the amount of light absorbed by the block
     */
    float getLightOpacity(IWorldAccess access, Vect3i pos);

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
    float getResistance(WorldRef ref, IEntity exploder);

    /**
     * The item that represents this block on an inventory
     */
    IItem getItemBlock();

    /**
     * If the block was created by an IBlockDefinition this method will return this definition, otherwise will return null
     */
    IBlockDefinition getBlockDefinition();

    /**
     * @return true if the block is opaque, false otherwise
     */
    boolean isOpaque();

    /**
     * This method gets the BlockData with the default values
     *
     * @return the default IBlockData
     */
	IBlockData getDefaultBlockData();

	IBlockData getBlockDataFromMeta(int meta);

	int getMetaFromBlockData(IBlockData variant);

    ICreativeTab getCreativeTab();

	boolean canBeReplacedByOreGen(WorldRef ref, final Predicate<IBlockData> target);

    /**
     * Checks if entities can be inside the block
     * @param ref the block reference
     * @return true if entities can be inside the block, false otherwise
     */
    boolean isPassable(WorldRef ref);

    /**
     * The internal minecraft block
     * Useful to check interfaces and APIs outside Blacksmith
     */
    Object getInternalBlock();


}
