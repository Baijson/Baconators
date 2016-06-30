package baijson.baconators.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Settings {

	private static Configuration configuration;

	static Settings instance = new Settings ( );

	static ConfigCategory baSection = new ConfigCategory ( "baconator" );

	static public boolean baEnabled = true;
	static public boolean baTooltipsEnabled = true;
	static public int baMaxFoodCapacity = 64;
	static public String[] baAcceptedFoodList = new String[]{
		  "minecraft:cooked_porkchop"
	};


	static ConfigCategory caSection = new ConfigCategory ( "cluckinator" );

	static public boolean caEnabled = true;
	static public boolean caTooltipsEnabled = true;
	static public int caMaxFoodCapacity = 64;
	static public String[] caAcceptedFoodList = new String[]{
		  "minecraft:cooked_chicken"
	};


	static ConfigCategory jaSection = new ConfigCategory ( "jerkynator" );

	static public boolean jaEnabled = true;
	static public boolean jaTooltipsEnabled = true;
	static public int jaMaxFoodCapacity = 64;
	static public String[] jaAcceptedFoodList = new String[]{
		  "minecraft:cooked_beef"
	};


	static ConfigCategory doSection = new ConfigCategory ( "darkonator" );

	static public boolean doEnabled = false;
	static public boolean doTooltipsEnabled = true;
	static public int doMaxFoodCapacity = 64;
	static public String[] doAcceptedFoodList = new String[]{
		  "minecraft:cookie"
	};


	static ConfigCategory sqSection = new ConfigCategory ( "squeakenator" );

	static public boolean sqEnabled = false;
	static public boolean sqTooltipsEnabled = true;
	static public int sqMaxFoodCapacity = 64;
	static public String[] sqAcceptedFoodList = new String[]{
		  "minecraft:cookie"
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

		config.setCategoryPropertyOrder ( doSection.getQualifiedName ( ), order );
		config.addCustomCategoryComment ( doSection.getQualifiedName (),"Darkosto's thingie-me-bob item." );
		config.setCategoryPropertyOrder ( sqSection.getQualifiedName ( ), order );
		config.addCustomCategoryComment ( sqSection.getQualifiedName (),"McSqueaker11's cute 'lil mouse." );

		/**
		 *
		 */
		property = config.get ( baSection.getQualifiedName ( ), "Enabled", baEnabled,
			  "Set to false to disable this item. default=true" );
		baEnabled = property.getBoolean ( );

		property = config.get ( baSection.getQualifiedName ( ), "Advanced tooltips", baTooltipsEnabled,
			  "Detailed tooltips, populated with a list of accepted food items." );
		baTooltipsEnabled = property.getBoolean ( );

		property = config.get ( baSection.getQualifiedName ( ), "Maximum capacity", baMaxFoodCapacity,
			  "Maximum capacity this item can hold. default=64" );
		baMaxFoodCapacity = property.getInt ( );

		property = config.get ( baSection.getQualifiedName ( ), "Accepted food", baAcceptedFoodList,
			  "List of accepted food items, this item can hold." );
		baAcceptedFoodList = property.getStringList ( );

		/**
		 *
		 */
		property = config.get ( caSection.getQualifiedName ( ), "Enabled", caEnabled,
			  "Set to false to disable this item. default=true" );
		caEnabled = property.getBoolean ( );

		property = config.get ( caSection.getQualifiedName ( ), "Advanced tooltips", caTooltipsEnabled,
			  "Detailed tooltips, populated with a list of accepted food items." );
		caTooltipsEnabled = property.getBoolean ( );

		property = config.get ( caSection.getQualifiedName ( ), "Maximum capacity", caMaxFoodCapacity,
			  "Maximum capacity this item can hold. default=64" );
		caMaxFoodCapacity = property.getInt ( );

		property = config.get ( caSection.getQualifiedName ( ), "Accepted food", caAcceptedFoodList,
			  "List of accepted food items, this item can hold." );
		caAcceptedFoodList = property.getStringList ( );

		/**
		 *
		 */
		property = config.get ( jaSection.getQualifiedName ( ), "Enabled", jaEnabled,
			  "Set to false to disable this item. default=true" );
		jaEnabled = property.getBoolean ( );

		property = config.get ( jaSection.getQualifiedName ( ), "Advanced tooltips", jaTooltipsEnabled,
			  "Detailed tooltips, populated with a list of accepted food items." );
		jaTooltipsEnabled = property.getBoolean ( );

		property = config.get ( jaSection.getQualifiedName ( ), "Maximum capacity", jaMaxFoodCapacity,
			  "Maximum capacity this item can hold. default=64" );
		jaMaxFoodCapacity = property.getInt ( );

		property = config.get ( jaSection.getQualifiedName ( ), "Accepted food", jaAcceptedFoodList,
			  "List of accepted food items, this item can hold." );
		jaAcceptedFoodList = property.getStringList ( );

		/**
		 * Darko's Toast stick.
		 */
		property = config.get ( doSection.getQualifiedName ( ), "Enabled", doEnabled,
			  "Set to false to disable this item. default=false" );
		doEnabled = property.getBoolean ( );

		property = config.get ( doSection.getQualifiedName ( ), "Advanced tooltips", doTooltipsEnabled,
			  "Detailed tooltips, populated with a list of accepted food items." );
		doTooltipsEnabled = property.getBoolean ( );

		property = config.get ( doSection.getQualifiedName ( ), "Maximum capacity", doMaxFoodCapacity,
			  "Maximum capacity this item can hold. default=64" );
		doMaxFoodCapacity = property.getInt ( );

		property = config.get ( doSection.getQualifiedName ( ), "Accepted food", doAcceptedFoodList,
			  "List of accepted food items, this item can hold." );
		doAcceptedFoodList = property.getStringList ( );

		/**
		 * Squeakenator
		 */
		property = config.get ( sqSection.getQualifiedName ( ), "Enabled", sqEnabled,
			  "Set to false to disable this item. default=false" );
		sqEnabled = property.getBoolean ( );

		property = config.get ( sqSection.getQualifiedName ( ), "Advanced tooltips", sqTooltipsEnabled,
			  "Detailed tooltips, populated with a list of accepted food items." );
		sqTooltipsEnabled = property.getBoolean ( );

		property = config.get ( sqSection.getQualifiedName ( ), "Maximum capacity", sqMaxFoodCapacity,
			  "Maximum capacity this item can hold. default=64" );
		sqMaxFoodCapacity = property.getInt ( );

		property = config.get ( sqSection.getQualifiedName ( ), "Accepted food", sqAcceptedFoodList,
			  "List of accepted food items, this item can hold." );
		sqAcceptedFoodList = property.getStringList ( );
	}
}
