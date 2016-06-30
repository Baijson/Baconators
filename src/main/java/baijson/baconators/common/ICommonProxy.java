package baijson.baconators.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 *
 */
public interface ICommonProxy {

	/**
	 * @param event
	 */
	void init ( FMLPreInitializationEvent event );

	/**
	 * @param event
	 */
	void load ( FMLInitializationEvent event );

	/**
	 * @param event
	 */
	void post ( FMLPostInitializationEvent event );
}
