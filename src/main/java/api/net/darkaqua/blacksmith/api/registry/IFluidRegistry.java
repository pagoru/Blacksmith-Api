package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.fluid.IFluid;
import net.darkaqua.blacksmith.api.fluid.IFluidDefinition;

import java.util.List;

/**
 * Created by cout970 on 23/12/2015.
 */
public interface IFluidRegistry {

    IFluid registerFluidDefinition(IFluidDefinition def);

    IFluid getFluid(String fluidName);

    List<IFluid> getRegisteredFluids();
}
