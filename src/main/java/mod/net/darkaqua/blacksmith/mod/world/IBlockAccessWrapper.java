package net.darkaqua.blacksmith.mod.world;

import net.darkaqua.blacksmith.api.world.IIBlockAccess;
import net.minecraft.world.IBlockAccess;

/**
 * Created by cout970 on 08/11/2015.
 */
public class IBlockAccessWrapper implements IIBlockAccess {

    private IBlockAccess access;

    public IBlockAccessWrapper(IBlockAccess access) {
        this.access = access;
    }

    public IBlockAccess getIBlockAccess() {
        return access;
    }
}
