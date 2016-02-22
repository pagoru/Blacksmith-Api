package net.darkaqua.blacksmith.api.common.fluid;

import net.darkaqua.blacksmith.api.Game;
import net.darkaqua.blacksmith.api.common.storage.IDataCompound;

/**
 * Created by cout970 on 23/12/2015.
 */
public abstract class FluidStackFactory {

    protected static FluidStackFactory INSTANCE;

    public static IFluid WATER;
    public static IFluid LAVA;

    public static IFluidStack createFluidStack(IFluid fluid, int amount, IDataCompound data) {
        return INSTANCE.newFluidStack(fluid, amount, data);
    }

    public static IFluidStack createFluidStack(IFluid fluid, int amount) {
        return INSTANCE.newFluidStack(fluid, amount, null);
    }

    public static IFluidStack createFluidStack(String fluidName, int amount, IDataCompound data) {
        IFluid fluid = Game.getCommonHandler().getFluidRegistry().getFluid(fluidName);
        if (fluid == null) return null;
        return INSTANCE.newFluidStack(fluid, amount, data);
    }

    public static IFluidStack createFluidStack(String fluidName, int amount) {
        IFluid fluid = Game.getCommonHandler().getFluidRegistry().getFluid(fluidName);
        if (fluid == null) return null;
        return INSTANCE.newFluidStack(fluid, amount, null);
    }

    public static void saveFluidStack(IFluidStack stack, IDataCompound data) {
        INSTANCE.save(stack, data);
    }

    public static IFluidStack loadFluidStack(IDataCompound data) {
        return INSTANCE.load(data);
    }

    protected abstract IFluidStack load(IDataCompound data);

    protected abstract void save(IFluidStack stack, IDataCompound data);

    protected abstract IFluidStack newFluidStack(IFluid fluid, int amount, IDataCompound data);
}
