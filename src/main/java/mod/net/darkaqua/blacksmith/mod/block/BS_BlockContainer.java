package net.darkaqua.blacksmith.mod.block;

import net.darkaqua.blacksmith.api.block.IBlockContainerDefinition;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.mod.registry.TileEntityRegistry;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by cout970 on 08/11/2015.
 */
public class BS_BlockContainer extends BlockContainer{

    private IBlockContainerDefinition definition;

    public BS_BlockContainer(IBlockContainerDefinition def) {
        super(Material.iron);//Temp
        definition = def;
        setUnlocalizedName(def.getUnlocalizedName());
        setCreativeTab(MCInterface.fromCreativeTab(def.getCreativeTab()));
        setBlockBounds(def.getBlockBounds());
        setHardness(def.getBlockHardness());
        setLightLevel(def.getLightEmitted());
        setLightOpacity((int)(def.getLightOpacity()*255f));
        setResistance(def.getBlockResistance());
    }

    public int getRenderType(){
        return 3;
    }

    private void setBlockBounds(Cube c){
        setBlockBounds((float)c.minX(), (float)c.minY(), (float)c.minZ(), (float)c.maxX(), (float)c.maxY(), (float)c.maxZ());
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        ITileEntityDefinition tile = definition.createTileEntity(MCInterface.fromWorld(worldIn), MCInterface.fromIBlockVariant(getStateFromMeta(meta)));
        return MCInterface.toTileEntity(TileEntityRegistry.INSTANCE.createTileEntity(tile));
    }
}
