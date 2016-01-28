package net.darkaqua.blacksmith.api.render.model;

import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;

import java.util.Set;

/**
 * Created by cout970 on 27/01/2016.
 */
public interface IDynamicModel {

    Set<String> getParts();

    void setRenderData(WorldRef ref, Vect3d offset);

    void renderAll();

    void renderParts(String... parts);

    void renderPartsThatContains(String text);

    void renderAllExcludingParts(String... parts);
}
