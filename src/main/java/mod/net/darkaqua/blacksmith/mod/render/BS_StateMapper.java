package net.darkaqua.blacksmith.mod.render;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.resources.model.ModelResourceLocation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cout970 on 27/11/2015.
 */
public class BS_StateMapper implements IStateMapper {

    private final HashMap<IBlockState, ModelResourceLocation> map;

    public BS_StateMapper(Map<IBlockState, ModelResourceLocation> map){
        this.map = new HashMap<>(map);
    }

    @Override
    public Map<IBlockState, ModelResourceLocation> putStateModelLocations(Block block) {
        return map;
    }
}
