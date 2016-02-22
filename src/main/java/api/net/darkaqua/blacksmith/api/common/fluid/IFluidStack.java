package net.darkaqua.blacksmith.api.common.fluid;

import net.darkaqua.blacksmith.api.common.storage.IDataCompound;

/**
 * Created by cout970 on 06/12/2015.
 */
public interface IFluidStack {

    IFluid getFluid();

    int getAmount();

    void setAmount(int amount);

    IDataCompound getDataCompound();

    void setDataCompound(IDataCompound data);

    String getUnlocalizedName();

    String getLocalizedName();

    IFluidStack copy();
}
