package mod.baijson.baconators.recipe;

import mod.baijson.baconators.items.Baconator;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * File created by Baijson.
 */
public class DyeableRecipe implements IRecipe {

	/**
	 * @param inventory
	 * @param world
	 *
	 * @return
	 */
	@Override
	public boolean matches ( InventoryCrafting inventory, World world ) {
		boolean foundItem = false;
		boolean foundDyes = false;
		int foundDyesCount = 0;

		ItemStack matchingDye = null;

		for ( int i = 0; i < inventory.getSizeInventory ( ); i++ ) {
			ItemStack stack = inventory.getStackInSlot ( i );
			if ( stack != null ) {

				if ( stack.getItem ( ) instanceof Baconator ) {
					if ( foundItem )
						return false;

					foundItem = true;
				} else {
					if ( stack.getItem ( ) instanceof ItemDye ) {
						foundDyesCount++;

						if ( matchingDye == null ) {
							matchingDye = stack;
						} else {
							if ( matchingDye.isItemEqual ( stack ) ) {
								if ( foundDyesCount == 8 ) {
									foundDyes = true;
								}
							} else {
								return false;
							}
						}
					}
				}
			}
		}

		return ( foundItem && foundDyes );
	}

	/**
	 * @param inventory
	 *
	 * @return
	 */
	@Nullable
	@Override
	public ItemStack getCraftingResult ( InventoryCrafting inventory ) {
		ItemStack result = null;

		ItemStack origin = null;
		ItemStack colors = null;

		for ( int i = 0; i < inventory.getSizeInventory ( ); i++ ) {
			ItemStack stack = inventory.getStackInSlot ( i );

			if ( stack.getItem ( ) instanceof ItemDye && colors == null ) {
				colors = stack;
			} else if ( stack.getItem ( ) instanceof Baconator && origin == null ) {
				origin = stack;
			}
		}

		if ( colors != null & origin != null ) {
			Baconator baconator = ( Baconator ) origin.getItem ( );
			if ( baconator != null && baconator.getOptions ( ).getDyeable ( ) ) {
				result = origin.copy ( );
				result.setItemDamage ( colors.getMetadata ( ) );
				result.stackSize = 1;
			}
		}

		return result;
	}

	/**
	 * @return
	 */
	@Override
	public int getRecipeSize () {
		return 9;
	}

	/**
	 * @return
	 */
	@Nullable
	@Override
	public ItemStack getRecipeOutput () {
		return null;
	}

	/**
	 * @param inventory
	 *
	 * @return
	 */
	@Override
	public ItemStack[] getRemainingItems ( InventoryCrafting inventory ) {
		return new ItemStack[ getRecipeSize ( ) ];
	}
}
