package net.darkaqua.blacksmith.api.render.model.json;

import javafx.util.Pair;
import net.darkaqua.blacksmith.api.render.ResourceReference;

import java.util.List;

/**
 * Created by cout970 on 28/11/2015.
 */
public interface IJsonModel {

    String getModelName();

    boolean useAmbientOcclusion();

    JsonRenderDisplay getDisplay(JsonRenderPlace place);

    List<Pair<String, ResourceReference>> getTextures();

    List<IJsonModelElement> getElements();
}
