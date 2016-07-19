package mod.baijson.baconators.common;

import mod.baijson.baconators.config.Settings;
import mod.baijson.baconators.items.Registry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * File created by Baijson.
 */
public class CommonProxy implements ICommonProxy {

	@Override
	public void init ( FMLPreInitializationEvent event ) {
		Settings.init ( event );
		Registry.init ( );
	}

	@Override
	public void load ( FMLInitializationEvent event ) {
		// Empty.
	}

	@Override
	public void post ( FMLPostInitializationEvent event ) {
		// Empty.
	}
}
