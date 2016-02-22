package net.darkaqua.blacksmith.mod.common.fluid;

import net.darkaqua.blacksmith.api.common.fluid.IFluidDefinition;
import net.darkaqua.blacksmith.api.common.util.WorldRef;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by cout970 on 23/12/2015.
 */
public class BS_Fluid extends Fluid {

    private IFluidDefinition definition;

    public BS_Fluid(IFluidDefinition definition) {
        super(definition.getFluidName(), MCInterface.toResourceLocation(definition.getStillTexture()), MCInterface.toResourceLocation(definition.getFlowingTexture()));
        this.definition = definition;
        block = MCInterface.toBlock(definition.getBlock());
    }

    public IFluidDefinition getDefinition() {
        return definition;
    }

    /* Stack-based Accessors */
    public int getLuminosity(FluidStack stack) {
        return definition.getLuminosity(MCInterface.fromFluidStack(stack));
    }

    public int getDensity(FluidStack stack) {
        return definition.getDensity(MCInterface.fromFluidStack(stack));
    }

    public int getTemperature(FluidStack stack) {
        return definition.getTemperature(MCInterface.fromFluidStack(stack));
    }

    public int getViscosity(FluidStack stack) {
        return definition.getViscosity(MCInterface.fromFluidStack(stack));
    }

    public boolean isGaseous(FluidStack stack) {
        return definition.isGaseous(MCInterface.fromFluidStack(stack));
    }

    public int getColor(FluidStack stack) {
        return definition.getColor(MCInterface.fromFluidStack(stack)).getRGB();
    }

    public ResourceLocation getStill(FluidStack stack) {
        return MCInterface.toResourceLocation(definition.getStill(MCInterface.fromFluidStack(stack)));
    }

    public ResourceLocation getFlowing(FluidStack stack) {
        return MCInterface.toResourceLocation(definition.getFlowing(MCInterface.fromFluidStack(stack)));
    }

    /* World-based Accessors */
    public int getLuminosity(World world, BlockPos pos) {
        return definition.getLuminosity(new WorldRef(MCInterface.fromWorld(world), MCInterface.fromBlockPos(pos)));
    }

    public int getDensity(World world, BlockPos pos) {
        return definition.getDensity(new WorldRef(MCInterface.fromWorld(world), MCInterface.fromBlockPos(pos)));
    }

    public int getTemperature(World world, BlockPos pos) {
        return definition.getTemperature(new WorldRef(MCInterface.fromWorld(world), MCInterface.fromBlockPos(pos)));
    }

    public int getViscosity(World world, BlockPos pos) {
        return definition.getViscosity(new WorldRef(MCInterface.fromWorld(world), MCInterface.fromBlockPos(pos)));
    }

    public boolean isGaseous(World world, BlockPos pos) {
        return definition.isGaseous(new WorldRef(MCInterface.fromWorld(world), MCInterface.fromBlockPos(pos)));
    }

    public int getColor(World world, BlockPos pos) {
        return definition.getColor(new WorldRef(MCInterface.fromWorld(world), MCInterface.fromBlockPos(pos))).getRGB();
    }

    public ResourceLocation getStill(World world, BlockPos pos) {
        return MCInterface.toResourceLocation(definition.getStill(new WorldRef(MCInterface.fromWorld(world), MCInterface.fromBlockPos(pos))));
    }

    public ResourceLocation getFlowing(World world, BlockPos pos) {
        return MCInterface.toResourceLocation(definition.getFlowing(new WorldRef(MCInterface.fromWorld(world), MCInterface.fromBlockPos(pos))));
    }
}
