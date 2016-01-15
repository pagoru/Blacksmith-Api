package net.darkaqua.blacksmith.api.fluid;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.util.Color;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 23/12/2015.
 */
public interface IFluidDefinition {

    String getFluidName();

    ResourceReference getStillTexture();

    ResourceReference getFlowingTexture();

    String getUnlocalizedName(IFluidStack stack);

    IBlock getBlock();

    int getLuminosity(IFluidStack stack);

    int getDensity(IFluidStack stack);

    int getTemperature(IFluidStack stack);

    int getViscosity(IFluidStack stack);

    boolean isGaseous(IFluidStack stack);

    Color getColor(IFluidStack stack);

    ResourceReference getStill(IFluidStack stack);

    ResourceReference getFlowing(IFluidStack stack);

    int getLuminosity(WorldRef ref);

    int getDensity(WorldRef ref);

    int getTemperature(WorldRef ref);

    int getViscosity(WorldRef ref);

    boolean isGaseous(WorldRef ref);

    Color getColor(WorldRef ref);

    ResourceReference getStill(WorldRef ref);

    ResourceReference getFlowing(WorldRef ref);
}
