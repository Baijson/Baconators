package mod.baijson.baconators.client;

import mod.baijson.baconators.common.CommonProxy;
import mod.baijson.baconators.common.ICommonProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * File created by Baijson.
 */
public class ClientProxy extends CommonProxy implements ICommonProxy {

	@Override
	public void init ( FMLPreInitializationEvent event ) {
		super.init ( event );

		ModelHandler.init ( );
	}
}
