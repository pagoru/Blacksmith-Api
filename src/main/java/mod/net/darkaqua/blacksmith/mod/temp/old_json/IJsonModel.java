package net.darkaqua.blacksmith.mod.temp.old_json;

import javafx.util.Pair;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.render.model.RenderTransformation;

import java.util.List;

/**
 * Created by cout970 on 28/11/2015.
 */
public interface IJsonModel {

    String getModelName();

    boolean useAmbientOcclusion();

    RenderTransformation getDisplay(RenderPlace place);

    List<Pair<String, ResourceReference>> getTextures();

    List<IJsonModelElement> getElements();
}
