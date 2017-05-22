package mod.baijson.baconators.common;

import mod.baijson.baconators.config.Settings;
import mod.baijson.baconators.items.Registry;
import mod.baijson.skeleton.common.ISidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * File created by Baijson.
 */
public class CommonProxy implements ISidedProxy {

    @Override
    public void init(FMLPreInitializationEvent event) {
        Settings.init(event);
        Registry.init();
    }

    /**
     * @param event
     */
    @Override
    public void load(FMLInitializationEvent event) {
        //
    }

    /**
     * @param event
     */
    @Override
    public void post(FMLPostInitializationEvent event) {
        //
    }
}
