package net.darkaqua.blacksmith.mod.fluid;

import net.darkaqua.blacksmith.api.fluid.FluidStackFactory;
import net.darkaqua.blacksmith.api.fluid.IFluid;
import net.darkaqua.blacksmith.api.fluid.IFluidStack;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by cout970 on 23/12/2015.
 */
public class BS_FluidStackFactory extends FluidStackFactory {

    private BS_FluidStackFactory() {
    }

    public static void init() {
        INSTANCE = new BS_FluidStackFactory();
        WATER = MCInterface.fromFluid(FluidRegistry.WATER);
        LAVA = MCInterface.fromFluid(FluidRegistry.LAVA);
    }

    @Override
    protected IFluidStack load(IDataCompound data) {
        return MCInterface.fromFluidStack(FluidStack.loadFluidStackFromNBT(MCInterface.toNBTCompound(data)));
    }

    @Override
    protected void save(IFluidStack stack, IDataCompound data) {
        MCInterface.toFluidStack(stack).writeToNBT(MCInterface.toNBTCompound(data));
    }

    @Override
    protected IFluidStack newFluidStack(IFluid fluid, int amount, IDataCompound data) {
        return MCInterface.fromFluidStack(new FluidStack(MCInterface.toFluid(fluid), amount, MCInterface.toNBTCompound(data)));
    }
}
