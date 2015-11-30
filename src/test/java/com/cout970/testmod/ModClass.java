package com.cout970.testmod;

import com.cout970.testmod.blocks.TestBlock;
import net.darkaqua.blacksmith.api.event.EventSubscribe;
import net.darkaqua.blacksmith.api.event.modloader.IPreInitEvent;
import net.darkaqua.blacksmith.api.modloader.BlacksmithMod;
import net.darkaqua.blacksmith.api.registry.StaticAccess;
import net.darkaqua.blacksmith.api.util.Log;

/**
 * Created by cout970 on 24/11/2015.
 */
@BlacksmithMod(id = ModClass.MOD_ID, name = ModClass.MOD_NAME, version = ModClass.MOD_VERSION)
public class ModClass {

    public static final String MOD_ID = "mod_id";
    public static final String MOD_NAME = "mod_name";
    public static final String MOD_VERSION = "mod_version";


    @EventSubscribe
    public void preInit(IPreInitEvent event){
        Log.debug("TestMod preinit");
        StaticAccess.GAME.getBlockRegistry().registerBlockDefinition(new TestBlock(), "block_identifier");
    }

}
