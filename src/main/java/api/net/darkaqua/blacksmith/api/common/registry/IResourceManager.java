package net.darkaqua.blacksmith.api.common.registry;

import net.darkaqua.blacksmith.api.common.util.ResourceReference;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

/**
 * Created by cout970 on 20/12/2015.
 */
public interface IResourceManager {

    IResourceFile getResource(ResourceReference ref) throws IOException;

    Set<String> getResourceDomains();

    Object getInternalResourceManger();

    interface IResourceFile {

        InputStream getInputStream();

        ResourceReference getResourceReference();

        Object getInternalResource();
    }
}
