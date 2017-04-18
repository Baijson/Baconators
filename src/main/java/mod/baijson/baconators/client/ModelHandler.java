package mod.baijson.baconators.client;

import mod.baijson.baconators.config.Settings;
import mod.baijson.baconators.items.Baconator;
import mod.baijson.baconators.items.Registry;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * File created by Baijson.
 */
@SideOnly( Side.CLIENT )
public class ModelHandler {

	/**
	 *
	 */
	static public void init () {
		if ( Registry.baconator != null && Settings.baItemEnabled )
			register ( Registry.baconator );
		if ( Registry.cluckinator != null && Settings.caItemEnabled )
			register ( Registry.cluckinator );
		if ( Registry.jerkynator != null && Settings.jaItemEnabled )
			register ( Registry.jerkynator );
		if ( Registry.darkonator != null && Settings.daItemEnabled )
			register ( Registry.darkonator );
	}

	/**
	 * @param item
	 */
	static private void register ( Item item ) {
		if ( item instanceof Baconator ) {
			Baconator actual = ( Baconator ) item;
			if ( actual.getOptions ( ).getDyeable ( ) ) {
				for ( int i = 0; i < 16; i++ ) {
					ModelBakery.registerItemVariants ( actual,
						  new ModelResourceLocation ( actual.getRegistryName ( ), Integer.toString ( i ) ) );
					ModelLoader.setCustomModelResourceLocation ( actual, i,
						  new ModelResourceLocation ( actual.getRegistryName ( ), Integer.toString ( i ) ) );
				}
			} else {
				ModelLoader.setCustomModelResourceLocation ( actual, 0, new ModelResourceLocation ( actual.getRegistryName ( ), "0" ) );
			}
		}
	}
}
