package net.darkaqua.blacksmith.mod;

import net.darkaqua.blacksmith.api.Game;
import net.darkaqua.blacksmith.api.client.IClientHandler;
import net.darkaqua.blacksmith.api.common.ICommonHandler;
import net.darkaqua.blacksmith.api.server.IServerHandler;
import net.darkaqua.blacksmith.mod.client.ClientHandler;
import net.darkaqua.blacksmith.mod.common.CommonHandler;
import net.darkaqua.blacksmith.mod.common.registry.ServerHandler;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by cout970 on 22/02/2016.
 */
public class BSGame extends Game {

    private BSGame(){}

    public static void init() {
        INSTANCE = new BSGame();
    }

    @Override
    protected ICommonHandler abstractGetCommonHandler() {
        return CommonHandler.INSTANCE;
    }

    @Override
    protected IClientHandler abstractGetClientHandler() {
        return ClientHandler.INSTANCE;
    }

    @Override
    protected IServerHandler abstractGetServerHandler() {
        return ServerHandler.INSTANCE;
    }

    @Override
    public boolean abstractIsClient() {
        return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT;
    }

    @Override
    public boolean abstractIsServer() {
        return FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER;
    }

    @Override
    public boolean abstractIsDedicatedServer() {
        return FMLCommonHandler.instance().getSide() == Side.SERVER;
    }

    @Override
    public boolean abstractIsDeobfuscatedEnvironment() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }
}
