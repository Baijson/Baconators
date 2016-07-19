package mod.baijson.baconators.items;

import mod.baijson.baconators.assets.Helpers;
import mod.baijson.baconators.client.TooltipUtil;
import mod.baijson.baconators.config.IOptions;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;
import squeek.applecore.api.food.FoodValues;
import squeek.applecore.api.food.IEdible;
import squeek.applecore.api.food.ItemFoodProxy;

import javax.annotation.Nullable;
import java.util.List;

/**
 * File created by Baijson.
 */
@Optional.Interface( iface = "squeek.applecore.api.food.IEdible", modid = "AppleCore", striprefs = true )
public class Baconator extends Item implements IEdible {
	private IOptions itemOptions;

	/**
	 * @param resource
	 * @param options
	 *
	 * @return
	 */
	static public Baconator create ( ResourceLocation resource, IOptions options ) {
		Baconator self = new Baconator ( resource );
		self.register ( options );
		return self;
	}

	/**
	 * @param resource
	 */
	protected Baconator ( ResourceLocation resource ) {
		super ( );

		setRegistryName ( resource );
		setUnlocalizedName ( resource.getResourcePath ( ) );

		setMaxStackSize ( 1 );
		setMaxDamage ( 0 );

		setHasSubtypes ( true );

		setCreativeTab ( CreativeTabs.FOOD );
	}

	/**
	 * @param options
	 */
	protected void register ( IOptions options ) {
		if ( options.getEnabled ( ) ) {
			this.itemOptions = options;
			GameRegistry.register ( this );
		}
	}

	/**
	 * @param stack
	 * @param world
	 * @param player
	 * @param hand
	 *
	 * @return
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick ( ItemStack stack, World world, EntityPlayer player, EnumHand hand ) {
		if ( !player.isSneaking ( ) ) {
			if ( getCurrentItemStack ( stack ) != null && getStorageItemCount ( stack ) > 0 ) {
				if ( player.getFoodStats ( ).needFood ( ) ) {
					player.setActiveHand ( hand );
				}
			}
		} else {
			if ( !world.isRemote ) {
				setEnabled ( stack, !getEnabled ( stack ) );
				world.playSound ( null, player.getPosition ( ), Registry.toggleSound, SoundCategory.PLAYERS, 0.3F, ( float ) ( 0.5 + Math.random ( ) * 0.5 ) );
			}
		}
		return ActionResult.newResult ( EnumActionResult.SUCCESS, stack );
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
	 *
	 * @return
	 */
	@Override
	public boolean hasEffect ( ItemStack stack ) {
		return getEnabled ( stack );
	}

	/**
	 * @param stack
	 *
	 * @return
	 */
	public EnumAction getItemUseAction ( ItemStack stack ) {
		return EnumAction.EAT;
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
	 * @param stack
	 * @param world
	 * @param entityLiving
	 *
	 * @return
	 */
	@Nullable
	@Override
	public ItemStack onItemUseFinish ( ItemStack stack, World world, EntityLivingBase entityLiving ) {
		if ( getCurrentItemStack ( stack ) != null && getStorageItemCount ( stack ) > 0 ) {
			EntityPlayer player = ( ( entityLiving instanceof EntityPlayer ) ? ( EntityPlayer ) entityLiving : null );
			if ( player != null ) {

				FoodStats stats = player.getFoodStats ( );
				ItemFood foodItem = ( ItemFood ) getCurrentItemStack ( stack ).getItem ( );
				ItemStack foodStack = new ItemStack ( foodItem );
				if ( stats.needFood ( ) ) {
					decStorageItemCount ( stack, 1 );

					/**
					 * AppleCore or nay.
					 */
					if ( Loader.isModLoaded ( "AppleCore" ) ) {
						onEatenCompatibility ( foodStack, player );
					} else {
						player.getFoodStats ( ).addStats ( foodItem, foodStack );
					}

					// player.getFoodStats ( ).addStats ( foodItem, foodStack );

					if ( stats.getFoodLevel ( ) >= 20 ) {
						world.playSound ( null, player.getPosition ( ), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.3F, ( float ) ( 0.5 + Math.random ( ) * 0.5 ) );
					}
				}
			}
		}
		return stack;
	}

	/**
	 * @param stack
	 * @param world
	 * @param entity
	 * @param itemSlot
	 * @param holding
	 */
	@Override
	public void onUpdate ( ItemStack stack, World world, Entity entity, int itemSlot, boolean holding ) {
		if ( !world.isRemote ) {
			EntityPlayer player = ( EntityPlayer ) entity;

			/**
			 *
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
			 *
			 */
			if ( player.getFoodStats ( ).needFood ( ) && getEnabled ( stack ) ) {

				if ( getCurrentItemStack ( stack ) != null && getStorageItemCount ( stack ) > 0 ) {
					FoodStats stats = player.getFoodStats ( );
					ItemFood foodItem = ( ItemFood ) getCurrentItemStack ( stack ).getItem ( );
					ItemStack foodStack = new ItemStack ( foodItem );

					if ( ( stats.getFoodLevel ( ) + foodItem.getHealAmount ( stack ) ) <= 20 ) {
						decStorageItemCount ( stack, 1 );

						/**
						 * AppleCore or nay.
						 */
						if ( Loader.isModLoaded ( "AppleCore" ) ) {
							onEatenCompatibility ( foodStack, player );
						} else {
							player.getFoodStats ( ).addStats ( foodItem, foodStack );
						}

						//player.getFoodStats ( ).addStats ( foodItem, foodStack );

						if ( stats.getFoodLevel ( ) >= 20 ) {
							world.playSound ( null, player.getPosition ( ), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.3F, ( float ) ( 0.5 + Math.random ( ) * 0.5 ) );
						}

						return;
					}
				}
			}
		}
	}

	/**
	 * @param stack
	 * @param compare
	 *
	 * @return
	 */
	public boolean isItemStackAccepted ( ItemStack stack, ItemStack compare ) {

		if ( compare.getItem ( ) instanceof ItemFood ) {
			for ( int i = 0; i < itemOptions.getAcceptedFood ( ).length; i++ ) {
				try {
					ItemStack itemStack = Helpers.getItemStack ( itemOptions.getAcceptedFood ( )[ i ] );
					if ( itemStack != null && itemStack.isItemEqual ( compare ) ) {
						if ( itemStack.getItem ( ) instanceof ItemFood ) {
							return true;
						}
					}
				} catch ( Exception e ) {
					FMLLog.log ( Level.WARN, e.getMessage ( ) );
				}
			}
		}
		return false;
	}

	/**
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
	 * @param stack
	 * @param items
	 */
	public void setCurrentItemStack ( ItemStack stack, @Nullable ItemStack items ) {
		if ( items == null ) {
			if ( getTagCompound ( stack ).hasKey ( "storage_item" ) ) {
				// reset.
				getTagCompound ( stack ).removeTag ( "storage_item" );
			}
		} else {
			// store item in NBTTagCompound.
			NBTTagCompound compound = new NBTTagCompound ( );
			items.writeToNBT ( compound );
			getTagCompound ( stack ).setTag ( "storage_item", compound );
		}
	}

	/**
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
			if ( getStorageItemCount ( stack ) < itemOptions.getMaxCapacity ( ) ) {
				incStorageItemCount ( stack, 1 );
				inventory.decrStackSize ( slot, 1 );
			} else {
				return;
			}
		}
		inventory.markDirty ( );
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
	 * @param stack
	 * @param player
	 * @param tooltip
	 * @param advanced
	 */
	@SideOnly( Side.CLIENT )
	@Override
	public void addInformation ( ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced ) {
		TooltipUtil.construct ( tooltip, () -> {

			TooltipUtil.insert ( tooltip, I18n.format ( "item.tooltip.enabled",
				  I18n.format ( "item.tooltip.checked." + ( getEnabled ( stack ) ? "1" : "0" ) ) ) );

			String _current = String.format ( "&8[&r%s/%s&8]&r", getStorageItemCount ( stack ), itemOptions.getMaxCapacity ( ) );

			TooltipUtil.insert ( tooltip, I18n.format ( "item.tooltip.holding",
				  ( getCurrentItemStack ( stack ) != null ? getCurrentItemStack ( stack ).getDisplayName ( ) + " " + _current : I18n.format ( "item.tooltip.nothing" ) ) ) );

			if ( itemOptions.getTooltipsEnabled ( ) ) {
				TooltipUtil.insert ( tooltip, "" );
				TooltipUtil.insert ( tooltip, I18n.format ( "item.tooltip.accepts" ) );
				for ( int i = 0; i < itemOptions.getAcceptedFood ( ).length; i++ ) {
					try {
						ItemStack foodStack = Helpers.getItemStack ( itemOptions.getAcceptedFood ( )[ i ].toString ( ) );
						if ( foodStack != null ) {
							TooltipUtil.insert ( tooltip, String.format ( "%s %s",
								  I18n.format ( "item.tooltip.bullets" ),
								  foodStack.getDisplayName ( )
							) );
						}
					} catch ( Exception e ) {
						FMLLog.log ( Level.WARN, e.getMessage ( ) );
					}
				}
			}
		} );
	}

	/**
	 * @return
	 */
	public IOptions getOptions () {
		return this.itemOptions;
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

	/**
	 * @param stack
	 *
	 * @return
	 */
	@Optional.Method( modid = "AppleCore" )
	@Override
	public FoodValues getFoodValues ( ItemStack stack ) {
		if ( getStorageItemCount ( stack ) > 0 && getCurrentItemStack ( stack ) != null ) {
			ItemFood foodItem = ( ItemFood ) getCurrentItemStack ( stack ).getItem ( );
			if ( foodItem != null ) {
				return new FoodValues (
					  foodItem.getHealAmount ( stack ),
					  foodItem.getSaturationModifier ( stack )
				);
			} else {
				return new FoodValues ( 0, 0F );
			}
		}
		return new FoodValues ( 0, 0F );
	}

	/**
	 * @param itemStack
	 * @param player
	 */
	@Optional.Method( modid = "AppleCore" )
	public void onEatenCompatibility ( ItemStack itemStack, EntityPlayer player ) {
		player.getFoodStats ( ).addStats ( new ItemFoodProxy ( this ), itemStack );
	}
}
