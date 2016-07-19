package mod.baijson.baconators.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * File created by Baijson.
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