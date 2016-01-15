package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.fluid.IFluid;
import net.darkaqua.blacksmith.api.fluid.IFluidDefinition;
import net.darkaqua.blacksmith.api.registry.IFluidRegistry;
import net.darkaqua.blacksmith.mod.fluid.BS_Fluid;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by cout970 on 23/12/2015.
 */
public class FluidRegistryManager implements IFluidRegistry {

    public static final FluidRegistryManager INSTANCE = new FluidRegistryManager();
    private static final Map<IFluidDefinition, RegisteredFluid> registeredFluids = new HashMap<>();

    private FluidRegistryManager() {
    }

    @Override
    public IFluid registerFluidDefinition(IFluidDefinition def) {
        Fluid fluid = new BS_Fluid(def);

        if (!FluidRegistry.registerFluid(fluid)) {
            fluid = FluidRegistry.getFluid(fluid.getName());
        }
        IFluid iFluid = MCInterface.fromFluid(fluid);
        registeredFluids.put(def, new RegisteredFluid(def, fluid, iFluid));
        return iFluid;
    }

    @Override
    public IFluid getFluid(String fluidName) {
        return MCInterface.fromFluid(FluidRegistry.getFluid(fluidName));
    }

    @Override
    public List<IFluid> getRegisteredFluids() {
        return FluidRegistry.getRegisteredFluids().entrySet().stream().map(e -> MCInterface.fromFluid(e.getValue())).collect(Collectors.toCollection(LinkedList::new));
    }

    public RegisteredFluid getRegisteredFluid(IFluidDefinition def) {
        return registeredFluids.get(def);
    }

    public static class RegisteredFluid {
        private IFluidDefinition definition;
        private Fluid fluid;
        private IFluid iFluid;

        public RegisteredFluid(IFluidDefinition definition, Fluid fluid, IFluid iFluid) {
            this.definition = definition;
            this.fluid = fluid;
            this.iFluid = iFluid;
        }

        public IFluidDefinition getDefinition() {
            return definition;
        }

        public Fluid getFluid() {
            return fluid;
        }

        public IFluid getiFluid() {
            return iFluid;
        }
    }
}
