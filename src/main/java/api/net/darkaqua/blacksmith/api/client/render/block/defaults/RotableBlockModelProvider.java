package net.darkaqua.blacksmith.api.client.render.block.defaults;

import net.darkaqua.blacksmith.api.common.block.IRotableBlock;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.modloader.IModIdentifier;
import net.darkaqua.blacksmith.api.common.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.client.render.model.IModelPart;
import net.darkaqua.blacksmith.api.client.render.model.IPartIdentifier;
import net.darkaqua.blacksmith.api.client.render.model.IStaticModel;
import net.darkaqua.blacksmith.api.client.render.model.defaults.SimpleModelPartBlock;
import net.darkaqua.blacksmith.api.client.render.block.IBlockModelProvider;
import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.ResourceReference;

import java.util.EnumMap;
import java.util.function.Function;

/**
 * Created by cout970 on 06/02/2016.
 */
public class RotableBlockModelProvider implements IBlockModelProvider {

    private Direction defaultDir;
    private EnumMap<Direction, IStaticModel> models;
    private IRotableBlock rotator;
    private IModIdentifier mod;
    private Function<Direction, ResourceReference> textures;

    public RotableBlockModelProvider(Direction defaultDir, IRotableBlock block, IModIdentifier mod, Function<Direction, ResourceReference> textures) {
        this.defaultDir = defaultDir;
        models = new EnumMap<>(Direction.class);
        this.rotator = block;
        this.mod = mod;
        this.textures = textures;
    }

    @Override
    public IStaticModel getModelForBlockData(IBlockData variant) {
        return models.get(rotator.getActualRotation(variant));
    }

    @Override
    public IStaticModel getModelForItemBlock(IItemStack stack) {
        return models.get(defaultDir);
    }

    @Override
    public void reloadModels(IModelRegistry registry) {
        models.clear();
        for(Direction dir : rotator.getValidRotations()){
            IModelPart part = new SimpleModelPartBlock(getTextureGetter(dir));
            IPartIdentifier id = registry.registerModelPart(mod, part);
            IStaticModel model = new SimpleBlockModelProvider.BlockModel(id);
            models.put(dir, model);
        }
    }

    private Function<Direction, ResourceReference> getTextureGetter(Direction dir) {
        if (dir == Direction.NORTH)
            return textures;
        if (dir == Direction.SOUTH)
            return direction -> textures.apply(direction.opposite());
        if (dir == Direction.EAST)
            return direction -> textures.apply(direction.rotate(Direction.Axis.Y, false));
        if (dir == Direction.WEST)
            return direction -> textures.apply(direction.rotate(Direction.Axis.Y, true));
        if (dir == Direction.UP)
            return direction -> textures.apply(direction.rotate(Direction.Axis.X, true));
        if (dir == Direction.DOWN)
            return direction -> textures.apply(direction.rotate(Direction.Axis.X, false));
        return textures;
    }
}
