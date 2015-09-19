package net.darkaqua.blacksmith.mod.core;

import org.objectweb.asm.Type;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import net.darkaqua.blacksmith.api.modloader.BlacksmithMod;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModContainerFactory;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class BlacksmithCore extends DummyModContainer{

	public static BlacksmithCore INSTANCE;
	public static final String MOD_ID = "Blacksmith";
	public static final String MOD_NAME = "Blacksmith";
	public static final String MOD_VERSION = "0.0.0";
	
	public BlacksmithCore() {
		super(new ModMetadata());
		INSTANCE = this;
		ModContainerFactory.instance().registerContainerType(Type.getType(BlacksmithMod.class), BlacksmithModContainer.class);
	}
	
	@Subscribe
	public void preInit(FMLPreInitializationEvent event) {
		
	}
	
	@Subscribe
	public void Init(FMLInitializationEvent event) {
		
	}
	
	@Subscribe
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(INSTANCE);
		return true;
	}

	@Override
	public Object getMod() {
		return this;
	}

	@Override
	public String getModId() {
		return MOD_ID;
	}

	@Override
	public String getName() {
		return MOD_NAME;
	}

	@Override
	public String getVersion() {
		return MOD_VERSION;
	}

	@Override
	public boolean matches(Object mod) {
		return mod == INSTANCE;
	}

	@Override
	public String getDisplayVersion() {
		return getVersion();
	}
}
