package net.darkaqua.blacksmith.api.client.render.model;

import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Created by cout970 on 27/01/2016.
 */
public interface IDynamicModel {

    void setOffset(Vect3d offset);

    void renderPartSet(IPartSet set);

    default void renderAll(){
        renderPartSet(getTotalPartSet());
    }

    IPartSet getTotalPartSet();

    IPartSet createFromNames(String... parts);

    IPartSet createExcludingNames(String... parts);

    IPartSet createAllContains(String text);

    IPartSet createAllNotContains(String text);

    IPartSet createFromFilter(Predicate<String> filter);

    interface IPartSet {
        Set<String> getPartNames();
    }
}
