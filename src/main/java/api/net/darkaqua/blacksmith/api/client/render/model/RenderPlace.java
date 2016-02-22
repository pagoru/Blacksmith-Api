package net.darkaqua.blacksmith.api.client.render.model;

/**
 * Created by cout970 on 28/11/2015.
 */
public enum RenderPlace {
    NONE("none"),
    THIRD_PERSON("thirdperson"),
    THIRD_PERSON_RIGHT_HAND("thirdperson_righthand"),
    THIRD_PERSON_LEFT_HAND("thirdperson_lefthand"),
    FIRST_PERSON("firstperson"),
    FIRST_PERSON_RIGHT_HAND("firstperson_righthand"),
    FIST_PERSON_LEFT_HAND("firstperson_lefthand"),
    GUI("gui"),
    HEAD("head"),
    GROUND("ground"),
    FIXED("fixed");

    private String propertyName;

    RenderPlace(String name) {
        propertyName = name;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
