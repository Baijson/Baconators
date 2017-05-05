package mod.baijson.baconators;

import mod.baijson.baconators.common.ICommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * File created by Baijson.
 */
@Mod(modid = Baconators.MODID, name = Baconators.MODNM, version = Baconators.VERSION, dependencies = Baconators.DEPENDENCIES)
public class Baconators {

    public static final String MODID = "baconators";
    public static final String MODNM = "Baconators";

    public static final String VERSION = "3.1.1";

    public static final String PROXY_COMMON = "mod.baijson." + MODID + ".common.CommonProxy";
    public static final String PROXY_CLIENT = "mod.baijson." + MODID + ".client.ClientProxy";

    /**
     * Dependencies.
     */
    public static final String DEPENDENCIES = "required-after:skeleton@[1.1.0,);";

    /**
     * Instance
     */
    @Mod.Instance(value = MODID)
    static public Baconators instance;

    /**
     * Proxy setup
     */
    @SidedProxy(clientSide = PROXY_CLIENT, serverSide = PROXY_COMMON)
    static public ICommonProxy proxy;

    /**
     * @param event
     */
    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event) {
        proxy.init(event);
    }

    /**
     * @param event
     */
    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        instance = this;
    }
}
