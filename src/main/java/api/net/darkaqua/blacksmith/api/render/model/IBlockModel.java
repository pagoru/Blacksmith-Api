package net.darkaqua.blacksmith.api.render.model;

import javafx.util.Pair;
import net.darkaqua.blacksmith.api.render.TextureLocation;

import java.util.List;

/**
 * Created by cout970 on 28/11/2015.
 */
public interface IBlockModel {

    String getModelName();

    IBlockModel getParent();

    boolean useAmbientOcclusion();

    Display getDisplay(RenderPlace place);

    List<Pair<String, TextureLocation>> getTextures();

    List<IModelElement> getElements();
}
