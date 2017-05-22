package mod.baijson.baconators.client;

import mod.baijson.baconators.common.CommonProxy;
import mod.baijson.skeleton.common.ISidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * File created by Baijson.
 */
public class ClientProxy extends CommonProxy implements ISidedProxy {

    @Override
    public void init(FMLPreInitializationEvent event) {
        super.init(event);

        ModelHandler.init();
    }

    /**
     * @param event
     */
    @Override
    public void load(FMLInitializationEvent event) {
        super.load(event);
    }

    /**
     * @param event
     */
    @Override
    public void post(FMLPostInitializationEvent event) {
        super.post(event);
    }
}
