package com.cout970.testmod;

import com.cout970.testmod.blocks.TestBlock;
import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.config.ConfigurationFactory;
import net.darkaqua.blacksmith.api.config.IConfiguration;
import net.darkaqua.blacksmith.api.event.EventSubscribe;
import net.darkaqua.blacksmith.api.event.modloader.IPreInitEvent;
import net.darkaqua.blacksmith.api.modloader.BlacksmithMod;
import net.darkaqua.blacksmith.api.registry.StaticAccess;
import net.darkaqua.blacksmith.api.render.ResourceReference;
import net.darkaqua.blacksmith.api.render.model.default_models.SimpleGenModelCube;
import net.darkaqua.blacksmith.mod.util.Log;

import java.io.File;

/**
 * Created by cout970 on 24/11/2015.
 */
@BlacksmithMod(id = ModClass.MOD_ID, name = ModClass.MOD_NAME, version = ModClass.MOD_VERSION)
public class ModClass {

    public static final String MOD_ID = "mod_id";
    public static final String MOD_NAME = "mod_name";
    public static final String MOD_VERSION = "mod_version";

    public static IBlock block;

    @EventSubscribe
    public void preInit(IPreInitEvent event){
        Log.debug("TestMod preinit");
        IBlockDefinition def = new TestBlock();
        block = StaticAccess.GAME.getBlockRegistry().registerBlockDefinition(def, "block_identifier");
        StaticAccess.GAME.getRenderRegistry().register(def, new SimpleGenModelCube(new ResourceReference(MOD_ID, "blocks/texture_name")));
        Log.debug("TestMod preinit done");
    }

    
    private static void testConfig(File c){
        IConfiguration config = ConfigurationFactory.create(c);

        config.load();
        String category = IConfiguration.CATEGORY_GENERAL;
        String a1 = config.getString(category, "string_key", "defaultValue", "comment");
        int a2 = config.getInteger(category, "int_key", -1, "comment");
        boolean a3 = config.getBoolean(category, "boolean_key", true, "comment");
        double a4 = config.getDouble(category, "double_key", -1, "comment");

        String[] a5 = config.getStringArray(category, "string_list_key", new String[]{"hello", "bye"}, "comment");
        int[] a6 = config.getIntegerArray(category, "int_list_key", new int[]{1,2}, "comment");
        boolean[] a7 = config.getBooleanArray(category, "boolean_list_key", new boolean[]{true, false}, "comment");
        double[] a8 = config.getDoubleArray(category, "double_list_key", new double[]{-1,0,1}, "comment");

        String a9 = config.getString(category, "String_l__key", "default string", "comment",new String[]{"default string", "valid values"});
        int a10 = config.getInteger(category, "int_l_key", -1, "comment", -10, 10);
        double a11 = config.getDouble(category, "double_l_key", -2D, "comment", -20, 20);

        String[] a12 = config.getStringArray(category, "String_list_l_key",new String[]{"default string", "valid values"}, "comment",new String[]{"default string", "valid values"});
        int[] a13 = config.getIntegerArray(category, "int_list_l_key",new int[]{5,6,7}, "comment", -1, 12);
        double[] a14 = config.getDoubleArray(category, "double_l_key",new double[]{2,3,4}, "comment", -2, 13);

        Log.debug(a1);
        Log.debug(a2);
        Log.debug(a3);
        Log.debug(a4);
        Log.debug(a5);
        Log.debug(a6);
        Log.debug(a7);
        Log.debug(a8);
        Log.debug(a9);
        Log.debug(a10);
        Log.debug(a11);
        Log.debug(a12);
        Log.debug(a13);
        Log.debug(a14);
        
        if(config.hasChanged())
            config.save();
    }
}
