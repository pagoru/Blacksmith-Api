package net.darkaqua.blacksmith.api.render;

/**
 * Created by cout970 on 15/11/2015.
 */
public class TextureLocation {

    private String domain;
    private String path;

    public TextureLocation( String domain, String fileName) {
        this.path = fileName;
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public String getPath() {
        return path;
    }

    public String toString(){
        return getDomain()+":"+getPath();
    }
}
