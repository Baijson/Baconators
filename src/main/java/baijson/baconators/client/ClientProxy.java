package baijson.baconators.client;

import baijson.baconators.common.CommonProxy;
import baijson.baconators.common.ICommonProxy;
import baijson.baconators.items.Registry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 *
 */
public class ClientProxy extends CommonProxy implements ICommonProxy {

	/**
	 * @param event
	 */
	@Override
	public void init ( FMLPreInitializationEvent event ) {
		super.init ( event );
	}

	/**
	 * @param event
	 */
	@Override
	public void load ( FMLInitializationEvent event ) {
		super.load ( event );

		Registry.load ( );
	}

	/**
	 * @param event
	 */
	@Override
	public void post ( FMLPostInitializationEvent event ) {
		super.post ( event );
	}
}
