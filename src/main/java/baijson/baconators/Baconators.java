package baijson.baconators;

import baijson.baconators.assets.Constants;
import baijson.baconators.assets.Logger;
import baijson.baconators.common.ICommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 *
 */
@Mod( modid = Constants.MOD_ID, name = Constants.MOD_ID, version = Constants.VERSION )
public class Baconators {

	/**
	 * Instance
	 */
	@Mod.Instance( value = Constants.MOD_ID )
	static public Baconators instance;

	/**
	 * Proxy setup
	 */
	@SidedProxy( clientSide = Constants.PROXY_CLIENT, serverSide = Constants.PROXY_COMMON )
	static public ICommonProxy proxy;

	/**
	 * @param event
	 */
	@Mod.EventHandler
	public void init ( FMLPreInitializationEvent event ) {
		Logger.Message ( "Feeding minecraft with Bacon! *burp*" );

		proxy.init ( event );
	}

	/**
	 * @param event
	 */
	@Mod.EventHandler
	public void load ( FMLInitializationEvent event ) {
		instance = this;

		proxy.load ( event );
	}

	/**
	 * @param event
	 */
	@Mod.EventHandler
	public void post ( FMLPostInitializationEvent event ) {
		proxy.post ( event );
	}
}
