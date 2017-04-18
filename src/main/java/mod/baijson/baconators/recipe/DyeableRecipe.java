package mod.baijson.baconators.recipe;

import mod.baijson.baconators.items.Baconator;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

/**
 * File created by Baijson.
 */
public class DyeableRecipe implements IRecipe {

    /**
     * @param inventory
     * @param world
     * @return
     */
    @Override
    public boolean matches(InventoryCrafting inventory, World world) {
        boolean foundItem = false;
        boolean foundDyes = false;
        int foundDyesCount = 0;

        ItemStack matchingDye = null;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != ItemStack.EMPTY) {
                if (stack.getItem() instanceof Baconator) {
                    if (foundItem)
                        return false;

                    foundItem = true;
                } else {
                    if (stack.getItem() instanceof ItemDye) {
                        foundDyesCount++;

                        if (matchingDye == null) {
                            matchingDye = stack;
                        } else {
                            if (matchingDye.isItemEqual(stack)) {
                                if (foundDyesCount == 8) {
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

        return (foundItem && foundDyes);
    }

    /**
     * @param inventory
     * @return
     */
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventory) {
        ItemStack result = ItemStack.EMPTY;

        ItemStack origin = null;
        ItemStack colors = null;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);

            System.out.println(stack.toString());

            if (stack.getItem() instanceof ItemDye && colors == null) {
                colors = stack;
            } else if (stack.getItem() instanceof Baconator && origin == null) {
                origin = stack;
            }
        }
        if (colors != null & origin != null) {
            result = origin.copy();
            result.setItemDamage(colors.getMetadata());
            result.setCount(1);
        }
        return result;
    }

    /**
     * @return
     */
    @Override
    public int getRecipeSize() {
        return 9;
    }

    /**
     * @return
     */
    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    /**
     * @param inventory
     * @return
     */
    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inventory) {
        NonNullList<ItemStack> remaining = NonNullList.<ItemStack>withSize(inventory.getSizeInventory(), ItemStack.EMPTY);

        for (int slot = 0; slot < remaining.size(); slot++) {
            ItemStack stackInSlot = inventory.getStackInSlot(slot);
            remaining.set(slot, ForgeHooks.getContainerItem(stackInSlot));
        }
        return remaining;
    }
}