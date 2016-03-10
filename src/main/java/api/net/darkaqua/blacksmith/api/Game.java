package net.darkaqua.blacksmith.api;

import net.darkaqua.blacksmith.api.client.IClientHandler;
import net.darkaqua.blacksmith.api.common.ICommonHandler;
import net.darkaqua.blacksmith.api.server.IServerHandler;

/**
 * Created by cout970 on 08/11/2015.
 */
public abstract class Game {

    protected static Game INSTANCE;

    public static ICommonHandler getCommonHandler(){
        return INSTANCE.abstractGetCommonHandler();
    }

    public static IClientHandler getClientHandler(){
        return INSTANCE.abstractGetClientHandler();
    }

    public static IServerHandler getServerHandler(){
        return INSTANCE.abstractGetServerHandler();
    }

    public static boolean isClient(){
        return INSTANCE.abstractIsClient();
    }

    public static boolean isServer(){
        return INSTANCE.abstractIsServer();
    }

    public static boolean isDedicatedServer(){
        return INSTANCE.abstractIsDedicatedServer();
    }

    public static boolean isDeobfuscatedEnvironment(){
        return INSTANCE.abstractIsDeobfuscatedEnvironment();
    }

    protected abstract ICommonHandler abstractGetCommonHandler();
    protected abstract IClientHandler abstractGetClientHandler();
    protected abstract IServerHandler abstractGetServerHandler();

    protected abstract boolean abstractIsClient();
    protected abstract boolean abstractIsServer();
    protected abstract boolean abstractIsDedicatedServer();
    protected abstract boolean abstractIsDeobfuscatedEnvironment();
}
