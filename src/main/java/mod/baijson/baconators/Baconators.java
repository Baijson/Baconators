package mod.baijson.baconators;

import mod.baijson.baconators.assets.Reference;
import mod.baijson.baconators.common.ICommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * File created by Baijson.
 */
@Mod( modid = Reference.MODID, name = Reference.MODNM, version = Reference.VERSION )
public class Baconators {

	/**
	 * Instance
	 */
	@Mod.Instance( value = Reference.MODID )
	static public Baconators instance;

	/**
	 * Proxy setup
	 */
	@SidedProxy( clientSide = Reference.PROXY_CLIENT, serverSide = Reference.PROXY_COMMON )
	static public ICommonProxy proxy;

	/**
	 * @param event
	 */
	@Mod.EventHandler
	public void init ( FMLPreInitializationEvent event ) {
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
