package net.darkaqua.blacksmith.mod.block;

import net.darkaqua.blacksmith.api.block.BlockMaterialFactory;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.material.Material;

/**
 * Created by cout970 on 29/12/2015.
 */
public class BS_BlockMaterialFactory extends BlockMaterialFactory {

    public static void init() {
        INSTANCE = new BS_BlockMaterialFactory();
        //@formatter:off
        AIR = MCInterface.toMaterial(Material.air);
        GRASS = MCInterface.toMaterial(Material.grass);
        GROUND = MCInterface.toMaterial(Material.ground);
        WOOD = MCInterface.toMaterial(Material.wood);
        ROCK = MCInterface.toMaterial(Material.rock);
        IRON = MCInterface.toMaterial(Material.iron);
        ANVIL = MCInterface.toMaterial(Material.anvil);
        WATER = MCInterface.toMaterial(Material.water);
        LAVA = MCInterface.toMaterial(Material.lava);
        LEAVES = MCInterface.toMaterial(Material.leaves);
        PLANTS = MCInterface.toMaterial(Material.plants);
        VINE = MCInterface.toMaterial(Material.vine);
        SPONGE = MCInterface.toMaterial(Material.sponge);
        CLOTH = MCInterface.toMaterial(Material.cloth);
        FIRE = MCInterface.toMaterial(Material.fire);
        SAND = MCInterface.toMaterial(Material.sand);
        CIRCUITS = MCInterface.toMaterial(Material.circuits);
        CARPET = MCInterface.toMaterial(Material.carpet);
        GLASS = MCInterface.toMaterial(Material.grass);
        REDSTONE_LIGHT = MCInterface.toMaterial(Material.redstoneLight);
        TNT = MCInterface.toMaterial(Material.tnt);
        CORAL = MCInterface.toMaterial(Material.coral);
        ICE = MCInterface.toMaterial(Material.ice);
        PACKED_ICE = MCInterface.toMaterial(Material.packedIce);
        SNOW = MCInterface.toMaterial(Material.snow);
        CRAFTED_SNOW = MCInterface.toMaterial(Material.craftedSnow);
        CACTUS = MCInterface.toMaterial(Material.cactus);
        CLAY = MCInterface.toMaterial(Material.clay);
        GOURD = MCInterface.toMaterial(Material.ground);
        DRAGON_EGG = MCInterface.toMaterial(Material.dragonEgg);
        PORTAL = MCInterface.toMaterial(Material.portal);
        CAKE = MCInterface.toMaterial(Material.cake);
        WEB = MCInterface.toMaterial(Material.web);
        PISTON = MCInterface.toMaterial(Material.piston);
        BARRIER = MCInterface.toMaterial(Material.barrier);
        //@formatter:on
    }

    private BS_BlockMaterialFactory() {
    }
}
