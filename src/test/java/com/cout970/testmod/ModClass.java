package com.cout970.testmod;

import com.cout970.testmod.blocks.StatefullBlock;
import com.cout970.testmod.blocks.TestBlock;
import com.cout970.testmod.gui.GuiTestHandler;
import com.cout970.testmod.items.TestItem;
import com.cout970.testmod.network.NetworkManager;
import com.cout970.testmod.tile.TileTestBlock;
import net.darkaqua.blacksmith.api.Game;
import net.darkaqua.blacksmith.api.client.render.block.defaults.EmptyBlockModelProvider;
import net.darkaqua.blacksmith.api.common.block.Blocks;
import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.common.config.ConfigurationFactory;
import net.darkaqua.blacksmith.api.common.config.IConfiguration;
import net.darkaqua.blacksmith.api.common.config.util.ConfigHandler;
import net.darkaqua.blacksmith.api.common.event.EventSubscribe;
import net.darkaqua.blacksmith.api.common.event.modloader.IInitEvent;
import net.darkaqua.blacksmith.api.common.event.modloader.IPreInitEvent;
import net.darkaqua.blacksmith.api.common.item.IItem;
import net.darkaqua.blacksmith.api.common.item.IItemDefinition;
import net.darkaqua.blacksmith.api.common.item.Items;
import net.darkaqua.blacksmith.api.common.modloader.BlacksmithMod;
import net.darkaqua.blacksmith.api.common.modloader.IModIdentifier;
import net.darkaqua.blacksmith.api.common.modloader.ModIdentifier;
import net.darkaqua.blacksmith.api.common.modloader.ModInstance;
import net.darkaqua.blacksmith.mod.common.util.Log;

import java.io.File;

/**
 * Created by cout970 on 24/11/2015.
 */
@BlacksmithMod(id = ModClass.MOD_ID, name = ModClass.MOD_NAME, version = ModClass.MOD_VERSION)
public class ModClass {

    public static final String MOD_ID = "mod_id";
    public static final String MOD_NAME = "mod_name";
    public static final String MOD_VERSION = "mod_version";


    @ModInstance
    public static ModClass instance;
    @ModIdentifier
    public static IModIdentifier MOD_IDENTIFIER;

    public static IBlock testBlock;
    public static IBlock blockStatefull;
    public static IItem item;
    public static ConfigHandler configHandler;
    public static final ConfigHolder CONFIG = new ConfigHolder();

    @EventSubscribe
    public void preInit(IPreInitEvent event) {
        Log.debug("TestMod preinit");

        configHandler = new ConfigHandler(CONFIG, ConfigurationFactory.create(event.getSuggestedConfigurationFile()));
        configHandler.read();
        configHandler.save();

        IBlockDefinition blockDef = new TestBlock();
        IItemDefinition itemDef = new TestItem();
        testBlock = Game.getCommonHandler().getBlockRegistry().registerBlockDefinition(blockDef, "block_identifier");
        item = Game.getCommonHandler().getItemRegistry().registerItemDefinition(itemDef, "item_identifier");
        IBlockDefinition statefull = new StatefullBlock();
        blockStatefull = Game.getCommonHandler().getBlockRegistry().registerBlockDefinition(statefull, "stateFullBlock");
        Game.getCommonHandler().getTileEntityRegistry().registerTileEntityDefinition(TileTestBlock.class, "TileTestBlock");

        if (Game.isClient()) {
            Game.getClientHandler().getRenderRegistry().registerBlockModelProvider(blockDef, new EmptyBlockModelProvider());
            Game.getClientHandler().getRenderRegistry().registerBlockModelProvider(statefull, new EmptyBlockModelProvider());
//            TileTestBlockRenderer.model = new TechneDynamicModel(TechneModelLoader.loadModel(new ResourceReference(MOD_ID, "models/test_block.tcn"), new ResourceReference(MOD_ID, "models/test_block")));
//            Game.getclientHandler().getRenderRegistry().registerItemModelProvider(itemDef,
//                    new PlaneItemModelProvider(new ResourceReference(MOD_ID, "items/texture_name")));
//
//            SimpleBlockModelProvider blockProvider = new SimpleBlockModelProvider(TechneModelLoader.loadModel(new ResourceReference(MOD_ID, "models/test_block.tcn"), new ResourceReference(MOD_ID, "models/test_block")));
//            Game.getclientHandler().getRenderRegistry().registerBlockModelProvider(blockDef, new EmptyBlockModelProvider());
//            Game.getclientHandler().getRenderRegistry().registerTileEntityRenderer(TileTestBlock.class, new TileTestBlockRenderer());
        }

        Game.getCommonHandler().getRecipeRegistry().addShapedCraftingRecipe(Blocks.ANVIL.newItemStack(1), "MMM", " T ", "III", 'M', Blocks.LOG, 'T', Items.STICK, 'I', Blocks.PLANKS);
        NetworkManager.init();


        Log.debug("Starting debug");
        Log.debug(Blocks.ANVIL.getBlock().getDefaultBlockData().getBlockDataHandler().getAttributes());
        Log.debug("Ending debug");

        Log.debug("TestMod preinit done");
    }

    @EventSubscribe
    public void init(IInitEvent event) {
        Log.debug("TestMod init");
        Game.getCommonHandler().getGuiRegistry().registerGuiCreationHandler(new GuiTestHandler());
        Log.debug("TestMod init done");
    }


    private static void testConfig(File c) {
        IConfiguration config = ConfigurationFactory.create(c);

        config.load();
        String category = IConfiguration.CATEGORY_GENERAL;
        String a1 = config.getString(category, "string_key", "defaultValue", "comment");
        int a2 = config.getInteger(category, "int_key", -1, "comment");
        boolean a3 = config.getBoolean(category, "boolean_key", true, "comment");
        double a4 = config.getDouble(category, "double_key", -1, "comment");

        String[] a5 = config.getStringArray(category, "string_list_key", new String[]{"hello", "bye"}, "comment");
        int[] a6 = config.getIntegerArray(category, "int_list_key", new int[]{1, 2}, "comment");
        boolean[] a7 = config.getBooleanArray(category, "boolean_list_key", new boolean[]{true, false}, "comment");
        double[] a8 = config.getDoubleArray(category, "double_list_key", new double[]{-1, 0, 1}, "comment");

        String a9 = config.getString(category, "String_l__key", "default string", "comment", new String[]{"default string", "valid values"});
        int a10 = config.getInteger(category, "int_l_key", -1, "comment", -10, 10);
        double a11 = config.getDouble(category, "double_l_key", -2D, "comment", -20, 20);

        String[] a12 = config.getStringArray(category, "String_list_l_key", new String[]{"default string", "valid values"}, "comment", new String[]{"default string", "valid values"});
        int[] a13 = config.getIntegerArray(category, "int_list_l_key", new int[]{5, 6, 7}, "comment", -1, 12);
        double[] a14 = config.getDoubleArray(category, "double_l_key", new double[]{2, 3, 4}, "comment", -2, 13);

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

        if (config.hasChanged())
            config.save();
    }
}
