package baijson.baconators.assets;

import cpw.mods.fml.common.registry.GameData;
import net.minecraft.item.ItemStack;

/**
 *
 */
public class Helpers {

	/**
	 * Return ItemStack by string. Return Subitem if metadata was provided.
	 * Example: mod:item,1
	 *
	 * @param name
	 * @return
	 */
	static public ItemStack getItemStack ( String name ) {
		ItemStack result = null;
		try {
			result = new ItemStack ( GameData.getItemRegistry ( ).getObject ( name.split ( "," )[ 0 ] ), 0,
				  ( name.split ( "," ).length > 1 ? Integer.parseInt ( name.split ( "," )[ 1 ] ) : 0 ) );

		} catch ( Exception Ex ) {
			Logger.Warning ( Ex.getMessage ( ) );
		}
		return result;
	}
}