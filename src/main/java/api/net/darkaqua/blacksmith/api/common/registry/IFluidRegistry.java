package net.darkaqua.blacksmith.api.common.registry;

import net.darkaqua.blacksmith.api.common.fluid.IFluid;
import net.darkaqua.blacksmith.api.common.fluid.IFluidDefinition;

import java.util.List;

/**
 * Created by cout970 on 23/12/2015.
 */
public interface IFluidRegistry {

    IFluid registerFluidDefinition(IFluidDefinition def);

    IFluid getFluid(String fluidName);

    List<IFluid> getRegisteredFluids();
}
