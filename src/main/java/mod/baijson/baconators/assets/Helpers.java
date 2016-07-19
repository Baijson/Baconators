package mod.baijson.baconators.assets;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Level;

/**
 *
 */
public class Helpers {

	/**
	 * Return ItemStack by string. Return Subitem if metadata was provided.
	 * Example: mod:item,1
	 * Example: mod:item:1
	 *
	 * @param name
	 *
	 * @return
	 */
	static public ItemStack getItemStack ( String name ) {
		ItemStack result = null;
		name = name.replace ( ",", ":" );
		try {
			if ( name.split ( ":" ).length > 2 ) {
				result = GameRegistry.makeItemStack ( name.substring ( 0, name.lastIndexOf ( ":" ) ), Integer.parseInt ( name.substring ( name.lastIndexOf ( ":" ) + 1 ) ), 64, "" );
			} else {
				result = GameRegistry.makeItemStack ( name, 0, 64, "" );
			}
		} catch ( Exception e ) {
			FMLLog.log ( Level.WARN, e.getMessage ( ) );
		}
		return result;
	}
}