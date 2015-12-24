package net.darkaqua.blacksmith.mod.fluid;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.fluid.IFluid;
import net.darkaqua.blacksmith.api.fluid.IFluidStack;
import net.darkaqua.blacksmith.api.util.Color;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by cout970 on 23/12/2015.
 */
public class FluidWrapper implements IFluid {

    private Fluid fluid;

    public FluidWrapper(Fluid fluid) {
        this.fluid = fluid;
    }

    public Fluid getFluid() {
        return fluid;
    }

    @Override
    public String getUnlocalizedName(IFluidStack stack) {
        return fluid.getUnlocalizedName(MCInterface.toFluidStack(stack));
    }

    @Override
    public IBlock getBlock() {
        return MCInterface.fromBlock(fluid.getBlock());
    }

    @Override
    public int getLuminosity(IFluidStack stack) {
        return fluid.getLuminosity(MCInterface.toFluidStack(stack));
    }

    @Override
    public int getDensity(IFluidStack stack) {
        return fluid.getDensity(MCInterface.toFluidStack(stack));
    }

    @Override
    public int getTemperature(IFluidStack stack) {
        return fluid.getTemperature(MCInterface.toFluidStack(stack));
    }

    @Override
    public int getViscosity(IFluidStack stack) {
        return fluid.getViscosity(MCInterface.toFluidStack(stack));
    }

    @Override
    public boolean isGaseous(IFluidStack stack) {
        return fluid.isGaseous(MCInterface.toFluidStack(stack));
    }

    @Override
    public Color getColor(IFluidStack stack) {
        return new Color(fluid.getColor(MCInterface.toFluidStack(stack)));
    }

    @Override
    public ResourceReference getStill(IFluidStack stack) {
        return MCInterface.fromResourceLocation(fluid.getStill(MCInterface.toFluidStack(stack)));
    }

    @Override
    public ResourceReference getFlowing(IFluidStack stack) {
        return MCInterface.fromResourceLocation(fluid.getFlowing(MCInterface.toFluidStack(stack)));
    }

    @Override
    public int getLuminosity(WorldRef ref) {
        return fluid.getLuminosity(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()));
    }

    @Override
    public int getDensity(WorldRef ref) {
        return fluid.getDensity(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()));
    }

    @Override
    public int getTemperature(WorldRef ref) {
        return fluid.getTemperature(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()));
    }

    @Override
    public int getViscosity(WorldRef ref) {
        return fluid.getViscosity(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()));
    }

    @Override
    public boolean isGaseous(WorldRef ref) {
        return fluid.isGaseous(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()));
    }

    @Override
    public Color getColor(WorldRef ref) {
        return new Color(fluid.getColor(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition())));
    }

    @Override
    public ResourceReference getStill(WorldRef ref) {
        return MCInterface.fromResourceLocation(fluid.getStill(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition())));
    }

    @Override
    public ResourceReference getFlowing(WorldRef ref) {
        return MCInterface.fromResourceLocation(fluid.getFlowing(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition())));
    }

    @Override
    public Object getInternalObject() {
        return fluid;
    }
}
