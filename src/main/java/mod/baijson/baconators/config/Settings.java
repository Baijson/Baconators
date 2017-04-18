package mod.baijson.baconators.config;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * File created by Baijson.
 */
public class Settings {

	private static Configuration configuration;

	static Settings instance = new Settings ( );


	static ConfigCategory baSection = new ConfigCategory ( "baconator" );
	static ConfigCategory caSection = new ConfigCategory ( "cluckinator" );
	static ConfigCategory jaSection = new ConfigCategory ( "jerkynator" );
	static ConfigCategory daSection = new ConfigCategory ( "darkonator" );

	static public boolean baItemEnabled = true;
	static public boolean caItemEnabled = false;
	static public boolean jaItemEnabled = false;
	static public boolean daItemEnabled = false;

	static public boolean baTTipEnabled = true;
	static public boolean caTTipEnabled = true;
	static public boolean jaTTipEnabled = true;
	static public boolean daTTipEnabled = true;

	static public boolean baDyeableItem = true;
	static public boolean caDyeableItem = true;
	static public boolean jaDyeableItem = true;
	static public boolean daDyeableItem = true;

	static public int baMaxFoodCapacity = 64;
	static public int caMaxFoodCapacity = 64;
	static public int jaMaxFoodCapacity = 64;
	static public int daMaxFoodCapacity = 64;

	static public String[] baAcceptedFoodList = new String[]{
		  "minecraft:cooked_porkchop"
	};
	static public String[] caAcceptedFoodList = new String[]{
		  "minecraft:cooked_chicken"
	};
	static public String[] jaAcceptedFoodList = new String[]{
		  "minecraft:cooked_beef"
	};
	static public String[] daAcceptedFoodList = new String[]{
		  "minecraft:bread"
	};

	/**
	 * @param event
	 */
	static public void init ( FMLPreInitializationEvent event ) {
		configuration = new Configuration ( event.getSuggestedConfigurationFile ( ), "1.0", false );
		MinecraftForge.EVENT_BUS.register ( instance );

		sync ( false );
	}

	/**
	 * @param load
	 */
	static public void sync ( boolean load ) {
		try {
			if ( load ) {
				configuration.load ( );
			}
			Settings.load ( configuration );
		} catch ( Exception e ) {
			// do nothing.
		} finally {
			if ( configuration.hasChanged ( ) ) {
				configuration.save ( );
			}
		}
	}

	/**
	 * @param config
	 */
	static public void load ( Configuration config ) {

		Property property;

		List<String> order = new ArrayList<String> ( );
		config.setCategoryPropertyOrder ( baSection.getQualifiedName ( ), order );
		config.setCategoryPropertyOrder ( caSection.getQualifiedName ( ), order );
		config.setCategoryPropertyOrder ( jaSection.getQualifiedName ( ), order );
		config.setCategoryPropertyOrder ( daSection.getQualifiedName ( ), order );

		/**
		 * Baconator
		 */
		property = config.get ( baSection.getQualifiedName ( ), "Enabled", baItemEnabled,
			  "Set to false to disable this item. default=true" );
		baItemEnabled = property.getBoolean ( );
		property = config.get ( baSection.getQualifiedName ( ), "Dyeable handles", baDyeableItem,
			  "Allow this item to be recolored with dyes. default=true" );
		baDyeableItem = property.getBoolean ( );
		property = config.get ( baSection.getQualifiedName ( ), "Advanced tooltips", baTTipEnabled,
			  "Detailed tooltips, populated with a list of accepted food items." );
		baTTipEnabled = property.getBoolean ( );
		property = config.get ( baSection.getQualifiedName ( ), "Maximum capacity", baMaxFoodCapacity,
			  "Maximum capacity this item can hold. default=64" );
		baMaxFoodCapacity = property.getInt ( );
		property = config.get ( baSection.getQualifiedName ( ), "Accepted food", baAcceptedFoodList,
			  "List of accepted food items, this item can hold." );
		baAcceptedFoodList = property.getStringList ( );

		/**
		 * Cluckinator
		 */
		property = config.get ( caSection.getQualifiedName ( ), "Enabled", caItemEnabled,
			  "Set to false to disable this item. default=false" );
		caItemEnabled = property.getBoolean ( );
		property = config.get ( caSection.getQualifiedName ( ), "Dyeable handles", caDyeableItem,
			  "Allow this item to be recolored with dyes. default=true" );
		caDyeableItem = property.getBoolean ( );
		property = config.get ( caSection.getQualifiedName ( ), "Advanced tooltips", caTTipEnabled,
			  "Detailed tooltips, populated with a list of accepted food items." );
		caTTipEnabled = property.getBoolean ( );
		property = config.get ( caSection.getQualifiedName ( ), "Maximum capacity", caMaxFoodCapacity,
			  "Maximum capacity this item can hold. default=64" );
		caMaxFoodCapacity = property.getInt ( );
		property = config.get ( caSection.getQualifiedName ( ), "Accepted food", caAcceptedFoodList,
			  "List of accepted food items, this item can hold." );
		caAcceptedFoodList = property.getStringList ( );

		/**
		 * Jerkynator
		 */
		property = config.get ( jaSection.getQualifiedName ( ), "Enabled", jaItemEnabled,
			  "Set to false to disable this item. default=false" );
		jaItemEnabled = property.getBoolean ( );
		property = config.get ( jaSection.getQualifiedName ( ), "Dyeable handles", jaDyeableItem,
			  "Allow this item to be recolored with dyes. default=true" );
		jaDyeableItem = property.getBoolean ( );
		property = config.get ( jaSection.getQualifiedName ( ), "Advanced tooltips", jaTTipEnabled,
			  "Detailed tooltips, populated with a list of accepted food items." );
		jaTTipEnabled = property.getBoolean ( );
		property = config.get ( jaSection.getQualifiedName ( ), "Maximum capacity", jaMaxFoodCapacity,
			  "Maximum capacity this item can hold. default=64" );
		jaMaxFoodCapacity = property.getInt ( );
		property = config.get ( jaSection.getQualifiedName ( ), "Accepted food", jaAcceptedFoodList,
			  "List of accepted food items, this item can hold." );
		jaAcceptedFoodList = property.getStringList ( );

		/**
		 * Darko's Toast stick.
		 */
		property = config.get ( daSection.getQualifiedName ( ), "Enabled", daItemEnabled,
			  "Set to false to disable this item. default=false" );
		daItemEnabled = property.getBoolean ( );
		property = config.get ( daSection.getQualifiedName ( ), "Dyeable handles", daDyeableItem,
			  "Allow this item to be recolored with dyes. default=true" );
		daDyeableItem = property.getBoolean ( );
		property = config.get ( daSection.getQualifiedName ( ), "Advanced tooltips", daTTipEnabled,
			  "Detailed tooltips, populated with a list of accepted food items." );
		daTTipEnabled = property.getBoolean ( );
		property = config.get ( daSection.getQualifiedName ( ), "Maximum capacity", daMaxFoodCapacity,
			  "Maximum capacity this item can hold. default=64" );
		daMaxFoodCapacity = property.getInt ( );
		property = config.get ( daSection.getQualifiedName ( ), "Accepted food", daAcceptedFoodList,
			  "List of accepted food items, this item can hold." );
		daAcceptedFoodList = property.getStringList ( );
	}
}
