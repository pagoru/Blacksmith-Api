package net.darkaqua.blacksmith.api.client.render.model.factory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.darkaqua.blacksmith.api.client.render.model.IModelFactory;
import net.darkaqua.blacksmith.api.client.render.model.IPartIdentifier;
import net.darkaqua.blacksmith.api.client.render.model.IStaticModel;
import net.darkaqua.blacksmith.api.client.render.model.defaults.ModelPartBlockSide;
import net.darkaqua.blacksmith.api.client.render.model.defaults.SimpleBlockModel;
import net.darkaqua.blacksmith.api.common.block.IRotableBlock;
import net.darkaqua.blacksmith.api.common.modloader.IModIdentifier;
import net.darkaqua.blacksmith.api.common.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.ResourceReference;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by cout970 on 13/03/2016.
 */
public class RotableBlockModelFactory implements IModelFactory {

    protected Map<Direction, ResourceReference> textures;
    protected IModIdentifier mod;
    protected IRotableBlock block;

    public RotableBlockModelFactory(IModIdentifier mod, IRotableBlock block, Map<Direction, ResourceReference> texture) {
        textures = Maps.newHashMap(texture);
        this.block = block;
    }

    @Override
    public Map<String, IStaticModel> createModels(IModelRegistry registry) {
        Map<String, IStaticModel> models = Maps.newHashMap();
        for (Direction dir : block.getValidRotations()) {
            List<IPartIdentifier> parts = Lists.newArrayList();
            for (Direction d : Direction.values()) {
                 parts.add(registry.registerModelPart(mod, new ModelPartBlockSide(d, getTextureGetter(dir, textures::get).apply(d))));
            }
            models.put(dir.name(), new SimpleBlockModel(parts));
        }
        return models;
    }

    private Function<Direction, ResourceReference> getTextureGetter(Direction dir, Function<Direction, ResourceReference> textures) {
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
