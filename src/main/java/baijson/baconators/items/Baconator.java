package baijson.baconators.items;

import baijson.baconators.assets.Constants;
import baijson.baconators.assets.Helpers;
import baijson.baconators.assets.Logger;
import baijson.baconators.client.Tooltips;
import baijson.baconators.config.IIOptions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

/**
 *
 */
public class Baconator extends Item {

	/**
	 * Baconator specific options from Config.
	 */
	private IIOptions options;

	// __0
	@SideOnly( Side.CLIENT )
	private IIcon[] ones;
	// _0_
	@SideOnly( Side.CLIENT )
	private IIcon[] tens;
	// 0__
	@SideOnly( Side.CLIENT )
	private IIcon[] huns;

	/**
	 *
	 */
	protected Baconator ( String name ) {
		super ( );

		setUnlocalizedName ( name );

		setMaxStackSize ( 1 );
		setMaxDamage ( 0 );

		setCreativeTab ( CreativeTabs.tabFood );
	}

	/**
	 * @param options
	 *
	 * @return
	 */
	static public Baconator create ( String name, IIOptions options ) {
		Baconator self = new Baconator ( name );
		self.options = options;

		return self;
	}

	/**
	 * Shift & rightclick to enable/disable this item.
	 *
	 * @param stack
	 * @param world
	 * @param player
	 *
	 * @return
	 */
	@Override
	public ItemStack onItemRightClick ( ItemStack stack, World world, EntityPlayer player ) {
		if ( !player.isSneaking ( ) ) {
			if ( getCurrentItemStack ( stack ) != null && getStorageItemCount ( stack ) > 0 ) {
				if ( player.getFoodStats ( ).needFood ( ) ) {
					player.setItemInUse ( stack, this.getMaxItemUseDuration ( stack ) );
				}
			}
		} else {
			if ( !world.isRemote ) {
				setEnabled ( stack, !getEnabled ( stack ) );
				world.playSoundAtEntity ( player, Constants.PREFIX + "sounds.toggle", 0.5F, world.rand.nextFloat ( ) * 0.1F + 0.9F );
			}
		}
		return stack;
	}

	/**
	 * @param stack
	 *
	 * @return
	 */
	public boolean getEnabled ( ItemStack stack ) {
		if ( getTagCompound ( stack ).hasKey ( "enabled" ) )
			return getTagCompound ( stack ).getBoolean ( "enabled" );
		return false;
	}

	/**
	 * @param stack
	 * @param value
	 */
	public void setEnabled ( ItemStack stack, boolean value ) {
		getTagCompound ( stack ).setBoolean ( "enabled", value );
	}

	/**
	 * @param stack
	 * @param world
	 * @param entity
	 * @param slot
	 * @param holding
	 */
	@Override
	public void onUpdate ( ItemStack stack, World world, Entity entity, int slot, boolean holding ) {

		if ( !world.isRemote ) {
			EntityPlayer player = ( EntityPlayer ) entity;

			/**
			 * When the player is sneaking and holding this item
			 * Loop through inventory and compare items against
			 * the accepted food list.
			 *
			 * If compared item is in the food list, add it in storage.
			 *
			 * @TODO decide if i want to store the first found item,
			 * @TODO or store the one with the highest or lowest food amount?
			 */
			if ( player.isSneaking ( ) && holding ) {
				for ( int i = 0; i < player.inventory.getSizeInventory ( ); i++ ) {
					ItemStack items = player.inventory.getStackInSlot ( i );
					if ( items != null && isItemStackAccepted ( stack, items ) ) {
						if ( getCurrentItemStack ( stack ) == null || getCurrentItemStack ( stack ).isItemEqual ( items ) ) {
							StoreItemStack ( stack, items, player.inventory, i );
							return;
						}
					}
				}
			}

			/**
			 * While the hunger level goes down, check to see if we can refill
			 * that exact amount of hunger.
			 *
			 * burp if hunger level is max. ( MAX: 20/2 = 10 nuggets )
			 */
			if ( player.getFoodStats ( ).needFood ( ) && getEnabled ( stack ) ) {
				//
				if ( getCurrentItemStack ( stack ) != null && getStorageItemCount ( stack ) > 0 ) {
					FoodStats stats = player.getFoodStats ( );
					ItemFood foodItem = ( ItemFood ) getCurrentItemStack ( stack ).getItem ( );
					ItemStack foodStack = new ItemStack ( foodItem );

					if ( ( stats.getFoodLevel ( ) + foodItem.func_150905_g ( foodStack ) ) <= 20 ) {
						decStorageItemCount ( stack, 1 );
						player.getFoodStats ( ).func_151686_a ( foodItem, foodStack );

						if ( stats.getFoodLevel ( ) >= 20 ) {
							world.playSoundAtEntity ( player, ":random.burp", 0.5F, world.rand.nextFloat ( ) * 0.1F + 0.9F );
						}
						return;
					}
				}
			}
		}
	}

	/**
	 * @param stack
	 * @param world
	 * @param player
	 *
	 * @return
	 */
	public ItemStack onEaten ( ItemStack stack, World world, EntityPlayer player ) {
		if ( getCurrentItemStack ( stack ) != null && getStorageItemCount ( stack ) > 0 ) {
			FoodStats stats = player.getFoodStats ( );
			ItemFood foodItem = ( ItemFood ) getCurrentItemStack ( stack ).getItem ( );
			ItemStack foodStack = new ItemStack ( foodItem );
			if ( stats.needFood ( ) ) {
				decStorageItemCount ( stack, 1 );
				player.getFoodStats ( ).func_151686_a ( foodItem, foodStack );
				if ( stats.getFoodLevel ( ) >= 20 ) {
					world.playSoundAtEntity ( player, ":random.burp", 0.5F, world.rand.nextFloat ( ) * 0.1F + 0.9F );
				}
			}
		}
		return stack;
	}

	/**
	 * Returns wheter or not an ItemStack can be stored inside this item.
	 * Method will also check if the items are instance of ItemFood.
	 *
	 * @param stack
	 * @param items
	 *
	 * @return
	 */
	public boolean isItemStackAccepted ( ItemStack stack, ItemStack items ) {
		if ( items.getItem ( ) instanceof ItemFood ) {
			for ( int i = 0; i < options.getAcceptedFood ( ).length; i++ ) {
				try {
					ItemStack compare = Helpers.getItemStack ( options.getAcceptedFood ( )[ i ] );
					if ( compare != null && compare.isItemEqual ( items ) ) {
						if ( compare.getItem ( ) instanceof ItemFood ) {
							return true;
						}
					}
				} catch ( Exception e ) {
					Logger.Warning ( e.getMessage ( ) );
				}
			}
		}
		return false;
	}

	/**
	 * Returns the current ItemStack being held by this item.
	 * null if empty.
	 *
	 * @param stack
	 *
	 * @return
	 */
	public ItemStack getCurrentItemStack ( ItemStack stack ) {

		return ( getTagCompound ( stack ).hasKey ( "storage_item" ) ?
			  ItemStack.loadItemStackFromNBT ( getTagCompound ( stack ).getCompoundTag ( "storage_item" ) )
			  : null );
	}

	/**
	 * Sets the current ItemStack being held by this item.
	 *
	 * @param stack
	 * @param item
	 */
	public void setCurrentItemStack ( ItemStack stack, ItemStack item ) {
		if ( item == null ) {
			if ( getTagCompound ( stack ).hasKey ( "storage_item" ) ) {
				// reset.
				getTagCompound ( stack ).removeTag ( "storage_item" );
			}
		} else {
			// store item in NBTTagCompound.
			NBTTagCompound compound = new NBTTagCompound ( );
			item.writeToNBT ( compound );
			getTagCompound ( stack ).setTag ( "storage_item", compound );
		}
	}


	/**
	 * Increase current item count with value.
	 *
	 * @param stack
	 * @param value
	 *
	 * @return
	 */
	public int incStorageItemCount ( ItemStack stack, int value ) {
		getTagCompound ( stack ).setInteger ( "storage_num", getStorageItemCount ( stack ) + value );
		return getStorageItemCount ( stack );
	}

	/**
	 * Decrease current item count with value.
	 * Unset current item being held, when outcome is lower then or equal to 0.
	 *
	 * @param stack
	 * @param value
	 *
	 * @return
	 */
	public int decStorageItemCount ( ItemStack stack, int value ) {
		if ( getStorageItemCount ( stack ) - value > 0 ) {
			getTagCompound ( stack ).setInteger ( "storage_num", getStorageItemCount ( stack ) - value );
		} else {
			getTagCompound ( stack ).setInteger ( "storage_num", 0 );
			setCurrentItemStack ( stack, null );
		}
		return getStorageItemCount ( stack );
	}

	/**
	 * Returns current item count being held by this item.
	 *
	 * @param stack
	 *
	 * @return
	 */
	public int getStorageItemCount ( ItemStack stack ) {
		if ( getTagCompound ( stack ).hasKey ( "storage_num" ) ) {
			return getTagCompound ( stack ).getInteger ( "storage_num" );
		}

		return 0;
	}

	/**
	 * Store an ItemStack into this item.
	 *
	 * @param stack
	 * @param items
	 * @param inventory
	 * @param slot
	 */
	public void StoreItemStack ( ItemStack stack, ItemStack items, InventoryPlayer inventory, int slot ) {
		if ( getCurrentItemStack ( stack ) == null ) {
			setCurrentItemStack ( stack, items );
		}

		for ( int i = 0; i < items.stackSize; i++ ) {
			if ( getStorageItemCount ( stack ) < options.getMaxCapacity ( ) ) {
				incStorageItemCount ( stack, 1 );
				inventory.decrStackSize ( slot, 1 );
			} else {
				return;
			}
		}
		inventory.markDirty ( );
	}

	/**
	 * @param stack
	 *
	 * @return
	 */
	public EnumAction getItemUseAction ( ItemStack stack ) {
		return EnumAction.eat;
	}

	/**
	 * @param stack
	 *
	 * @return
	 */
	public int getMaxItemUseDuration ( ItemStack stack ) {
		return 32;
	}

	/**
	 * Glow when enabled.
	 *
	 * @param stack
	 *
	 * @return
	 */
	@Override
	@SideOnly( Side.CLIENT )
	@SuppressWarnings( "deprecation" )
	public boolean hasEffect ( ItemStack stack ) {
		return getEnabled ( stack );
	}

	/**
	 * @param register
	 */
	@Override
	public void registerIcons ( IIconRegister register ) {
		ones = new IIcon[ 10 ];
		tens = new IIcon[ 10 ];
		huns = new IIcon[ 10 ];

		for ( int i = 0; i < 10; i++ ) {
			ones[ i ] = register.registerIcon ( Constants.PREFIX + "numbers/00" + i );
			tens[ i ] = register.registerIcon ( Constants.PREFIX + "numbers/0" + ( i == 0 ? i : i + "0" ) );
			huns[ i ] = register.registerIcon ( Constants.PREFIX + "numbers/" + ( i == 0 ? i : i + "00" ) );
		}
		itemIcon = register.registerIcon ( Constants.PREFIX + getUnlocalizedName ( ).substring ( getUnlocalizedName ( ).indexOf ( "." ) + 1 ).toLowerCase ( ) );
	}

	/**
	 * @param stack
	 * @param step
	 *
	 * @return
	 */
	@Override
	public IIcon getIcon ( ItemStack stack, int step ) {
		int num = getStorageItemCount ( stack );
		switch ( step ) {
			case 0:
				return itemIcon;
			case 1: // 0 0 X
				return ones[ num % 10 ];
			case 2: // 0 X 0
				return tens[ ( num / 10 ) % 10 ];
			case 3: // X 0 0
				return huns[ ( num / 100 ) % 100 ];
			default:
				return itemIcon;
		}
	}

	/**
	 * @param stack
	 *
	 * @return
	 */
	@Override
	public IIcon getIconIndex ( ItemStack stack ) {
		return itemIcon;
	}

	/**
	 * @param stack
	 * @param player
	 * @param tooltip
	 * @param advanced
	 */
	@Override
	@SideOnly( Side.CLIENT )
	public void addInformation ( ItemStack stack, EntityPlayer player, final List tooltip, boolean advanced ) {

		Tooltips.construct ( tooltip, () -> {

			Tooltips.insert ( tooltip, String.format (
				  StatCollector.translateToLocal ( "item.tooltip.enabled" ),
				  StatCollector.translateToLocal ( "item.tooltip.checked." + ( getEnabled ( stack ) ? "1" : "0" ) )
			) );

			String _current = String.format ( "&8[&r%s/%s&8]&r", getStorageItemCount ( stack ), options.getMaxCapacity ( ) );

			Tooltips.insert ( tooltip, String.format (
				  StatCollector.translateToLocal ( "item.tooltip.holding" ),
				  ( getCurrentItemStack ( stack ) != null ? getCurrentItemStack ( stack ).getDisplayName ( ) + " " + _current : StatCollector.translateToLocal ( "item.tooltip.nothing" ) )
			) );

			if ( options.getTooltipsEnabled ( ) ) {
				Tooltips.insert ( tooltip, "" );
				Tooltips.insert ( tooltip, StatCollector.translateToLocal ( "item.tooltip.accepts" ) );
				for ( int i = 0; i < options.getAcceptedFood ( ).length; i++ ) {
					try {
						ItemStack foodStack = Helpers.getItemStack ( options.getAcceptedFood ( )[ i ].toString ( ) );

						if ( foodStack != null ) {
							Tooltips.insert ( tooltip, String.format ( "%s %s",
								  StatCollector.translateToLocal ( "item.tooltip.bullets" ),
								  foodStack.getDisplayName ( )
							) );
						}

					} catch ( Exception Ex ) {
						Logger.Message ( Ex.getMessage ( ) );
					}
				}
			}
		} );
	}

	/**
	 * Returns NBTTagCompound. created if null.
	 *
	 * @param stack
	 *
	 * @return
	 */
	public NBTTagCompound getTagCompound ( ItemStack stack ) {
		if ( stack.getTagCompound ( ) == null )
			stack.setTagCompound ( new NBTTagCompound ( ) );
		return stack.getTagCompound ( );
	}
}
