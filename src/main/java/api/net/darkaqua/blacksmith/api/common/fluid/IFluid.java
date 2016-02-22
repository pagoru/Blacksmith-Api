package net.darkaqua.blacksmith.api.common.fluid;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.util.ResourceReference;
import net.darkaqua.blacksmith.api.common.util.WorldRef;

import java.awt.*;

/**
 * Created by cout970 on 06/12/2015.
 */
public interface IFluid {

    String getUnlocalizedName(IFluidStack stack);

    IBlock getBlock();

    int getLuminosity(IFluidStack stack);

    int getDensity(IFluidStack stack);

    int getTemperature(IFluidStack stack);

    int getViscosity(IFluidStack stack);

    boolean isGaseous(IFluidStack stack);

    Color getColor(IFluidStack stack);

    int getLuminosity(WorldRef ref);

    int getDensity(WorldRef ref);

    int getTemperature(WorldRef ref);

    int getViscosity(WorldRef ref);

    boolean isGaseous(WorldRef ref);

    Color getColor(WorldRef ref);

    // This part may change with the time
    ResourceReference getStill(IFluidStack stack);

    ResourceReference getFlowing(IFluidStack stack);

    ResourceReference getStill(WorldRef ref);

    ResourceReference getFlowing(WorldRef ref);

    Object getInternalObject();
}
