package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;

/**
 * Created by cout970 on 08/11/2015.
 */
public class DefaultBlockDefinition implements IBlockDefinition{

    protected String blockName;

    public DefaultBlockDefinition(String name){
        blockName = name;
    }

    public String getUnlocalizedName(){
        return blockName;
    }

    public String getLocalizedName(){
        return getUnlocalizedName();
    }
}
