package net.darkaqua.blacksmith.mod.common.fluid;

import net.darkaqua.blacksmith.api.common.fluid.IFluid;
import net.darkaqua.blacksmith.api.common.fluid.IFluidStack;
import net.darkaqua.blacksmith.api.common.storage.IDataCompound;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by cout970 on 23/12/2015.
 */
public class FluidStackWrapper implements IFluidStack {

    private FluidStack stack;

    public FluidStackWrapper(FluidStack stack) {
        this.stack = stack;
    }

    public FluidStack getFluidStack() {
        return stack;
    }

    @Override
    public int getAmount() {
        return stack.amount;
    }

    @Override
    public void setAmount(int amount) {
        stack.amount = amount;
    }

    @Override
    public IFluid getFluid() {
        return MCInterface.fromFluid(stack.getFluid());
    }

    @Override
    public IDataCompound getDataCompound() {
        return MCInterface.fromNBTCompound(stack.tag);
    }

    @Override
    public void setDataCompound(IDataCompound data) {
        stack.tag = MCInterface.toNBTCompound(data);
    }

    @Override
    public String getUnlocalizedName() {
        return stack.getUnlocalizedName();
    }

    @Override
    public String getLocalizedName() {
        return stack.getLocalizedName();
    }

    @Override
    public IFluidStack copy() {
        return MCInterface.fromFluidStack(stack.copy());
    }
}
