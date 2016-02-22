package net.darkaqua.blacksmith.api.common.fluid.defaults;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.fluid.IFluidDefinition;
import net.darkaqua.blacksmith.api.common.fluid.IFluidStack;
import net.darkaqua.blacksmith.api.common.util.ResourceReference;
import net.darkaqua.blacksmith.api.common.util.WorldRef;

import java.awt.*;

/**
 * Created by cout970 on 23/12/2015.
 */
public class DefaultFluidDefinition implements IFluidDefinition {

    protected String name;
    protected ResourceReference still;
    protected ResourceReference flowing;
    protected IBlock block;
    protected int luminosity = 0;
    protected int density = 1000;
    protected int temperature = 300;
    protected int viscosity = 1000;
    protected boolean isGaseous = false;

    public DefaultFluidDefinition(String name, ResourceReference still, ResourceReference flowing) {
        this.name = name;
        this.still = still;
        this.flowing = flowing;
    }

    public DefaultFluidDefinition(String name, ResourceReference still, ResourceReference flowing, IBlock block) {
        this.name = name;
        this.still = still;
        this.flowing = flowing;
        this.block = block;
    }

    @Override
    public String getFluidName() {
        return name;
    }

    @Override
    public ResourceReference getStillTexture() {
        return still;
    }

    @Override
    public ResourceReference getFlowingTexture() {
        return flowing;
    }

    @Override
    public String getUnlocalizedName(IFluidStack stack) {
        return name;
    }

    @Override
    public IBlock getBlock() {
        return block;
    }

    @Override
    public int getLuminosity(IFluidStack stack) {
        return luminosity;
    }

    @Override
    public int getDensity(IFluidStack stack) {
        return density;
    }

    @Override
    public int getTemperature(IFluidStack stack) {
        return temperature;
    }

    @Override
    public int getViscosity(IFluidStack stack) {
        return viscosity;
    }

    @Override
    public boolean isGaseous(IFluidStack stack) {
        return isGaseous;
    }

    @Override
    public Color getColor(IFluidStack stack) {
        return new Color(0xFFFFFF);
    }

    @Override
    public ResourceReference getStill(IFluidStack stack) {
        return still;
    }

    @Override
    public ResourceReference getFlowing(IFluidStack stack) {
        return flowing;
    }

    @Override
    public int getLuminosity(WorldRef ref) {
        return luminosity;
    }

    @Override
    public int getDensity(WorldRef ref) {
        return density;
    }

    @Override
    public int getTemperature(WorldRef ref) {
        return temperature;
    }

    @Override
    public int getViscosity(WorldRef ref) {
        return viscosity;
    }

    @Override
    public boolean isGaseous(WorldRef ref) {
        return isGaseous;
    }

    @Override
    public Color getColor(WorldRef ref) {
        return new Color(0xFFFFFF);
    }

    @Override
    public ResourceReference getStill(WorldRef ref) {
        return still;
    }

    @Override
    public ResourceReference getFlowing(WorldRef ref) {
        return flowing;
    }
}
