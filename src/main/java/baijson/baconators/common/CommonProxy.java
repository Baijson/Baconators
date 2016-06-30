package baijson.baconators.common;

import baijson.baconators.config.Settings;
import baijson.baconators.items.Registry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 *
 */
public class CommonProxy implements ICommonProxy {

	/**
	 * @param event
	 */
	@Override
	public void init ( FMLPreInitializationEvent event ) {
		Settings.init ( event );
		Registry.init ( );
	}

	/**
	 * @param event
	 */
	@Override
	public void load ( FMLInitializationEvent event ) {

	}

	/**
	 * @param event
	 */
	@Override
	public void post ( FMLPostInitializationEvent event ) {

	}
}
