package baijson.baconators.items;

import baijson.baconators.client.Renderer;
import baijson.baconators.config.IOptions;
import baijson.baconators.config.Settings;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.oredict.OreDictionary;

/**
 *
 */
public class Registry {

	static public Item baconator;
	static public Item cluckinator;
	static public Item jerkynator;
	static public Item darkonator;
	static public Item squeakenator;

	/**
	 * Create and register items.
	 */
	static public void init () {

		/**
		 * Baconator
		 */
		baconator = Baconator.create ( "Baconator", new IOptions (
			  Settings.baEnabled,
			  Settings.baAcceptedFoodList,
			  Settings.baMaxFoodCapacity,
			  Settings.baTooltipsEnabled
		) );

		if ( Settings.baEnabled ) {
			GameRegistry.registerItem ( baconator, "Baconator" );
			GameRegistry.addRecipe ( new ItemStack ( baconator, 1 ),
				  "WRW",
				  "IPI",
				  "IPI",
				  'W', new ItemStack ( Blocks.wool, 1, 6 ),
				  'R', new ItemStack ( Items.redstone ),
				  'I', new ItemStack ( Items.iron_ingot ),
				  'P', new ItemStack ( Blocks.planks, 1, OreDictionary.WILDCARD_VALUE )
			);
		}

		/**
		 * Cluckinator
		 */
		cluckinator = Baconator.create ( "Cluckinator", new IOptions (
			  Settings.caEnabled,
			  Settings.caAcceptedFoodList,
			  Settings.caMaxFoodCapacity,
			  Settings.caTooltipsEnabled
		) );

		if ( Settings.caEnabled ) {
			GameRegistry.registerItem ( cluckinator, "Cluckinator" );
			GameRegistry.addRecipe ( new ItemStack ( cluckinator, 1 ),
				  "WRW",
				  "IPI",
				  "IPI",
				  'W', new ItemStack ( Blocks.wool, 1, 0 ),
				  'R', new ItemStack ( Items.redstone ),
				  'I', new ItemStack ( Items.iron_ingot ),
				  'P', new ItemStack ( Blocks.planks, 1, OreDictionary.WILDCARD_VALUE )
			);
		}

		/**
		 * Jerkynator
		 */
		jerkynator = Baconator.create ( "Jerkynator", new IOptions (
			  Settings.jaEnabled,
			  Settings.jaAcceptedFoodList,
			  Settings.jaMaxFoodCapacity,
			  Settings.jaTooltipsEnabled
		) );

		if ( Settings.jaEnabled ) {
			GameRegistry.registerItem ( jerkynator, "Jerkynator" );
			GameRegistry.addRecipe ( new ItemStack ( jerkynator, 1 ),
				  "WRW",
				  "IPI",
				  "IPI",
				  'W', new ItemStack ( Blocks.wool, 1, 12 ),
				  'R', new ItemStack ( Items.redstone ),
				  'I', new ItemStack ( Items.iron_ingot ),
				  'P', new ItemStack ( Blocks.planks, 1, OreDictionary.WILDCARD_VALUE )
			);
		}

		/**
		 * Squeackenator
		 */
		squeakenator = Baconator.create ( "Squeakenator", new IOptions (
			  Settings.sqEnabled,
			  Settings.sqAcceptedFoodList,
			  Settings.sqMaxFoodCapacity,
			  Settings.sqTooltipsEnabled
		) );

		if ( Settings.sqEnabled ) {
			GameRegistry.registerItem ( squeakenator, "Squeakenator" );
			GameRegistry.addRecipe ( new ItemStack ( squeakenator, 1 ),
				  "WRW",
				  "IPI",
				  "IPI",
				  'W', new ItemStack ( Blocks.wool, 1, 1 ),
				  'R', new ItemStack ( Items.redstone ),
				  'I', new ItemStack ( Items.iron_ingot ),
				  'P', new ItemStack ( Blocks.planks, 1, OreDictionary.WILDCARD_VALUE )
			);
		}

		/**
		 * Darkonator
		 */
		darkonator = Baconator.create ( "Darkonator", new IOptions (
			  Settings.doEnabled,
			  Settings.doAcceptedFoodList,
			  Settings.doMaxFoodCapacity,
			  Settings.doTooltipsEnabled
		) );

		if ( Settings.doEnabled ) {
			GameRegistry.registerItem ( darkonator, "Darkonator" );
			GameRegistry.addRecipe ( new ItemStack ( darkonator, 1 ),
				  "WRW",
				  "IPI",
				  "IPI",
				  'W', new ItemStack ( Blocks.wool, 1, 15 ),
				  'R', new ItemStack ( Items.redstone ),
				  'I', new ItemStack ( Items.gold_ingot ),
				  'P', new ItemStack ( Blocks.planks, 1, OreDictionary.WILDCARD_VALUE )
			);
		}
	}


	/**
	 * Register item renderers.
	 */
	static public void load () {

		MinecraftForgeClient.registerItemRenderer ( baconator, new Renderer ( ) );
		MinecraftForgeClient.registerItemRenderer ( cluckinator, new Renderer ( ) );
		MinecraftForgeClient.registerItemRenderer ( jerkynator, new Renderer ( ) );

		MinecraftForgeClient.registerItemRenderer ( darkonator, new Renderer ( ) );
		MinecraftForgeClient.registerItemRenderer ( squeakenator, new Renderer ( ) );
	}
}
