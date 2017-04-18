package mod.baijson.baconators.items;

import mod.baijson.baconators.Baconators;
import mod.baijson.baconators.config.Settings;
import mod.baijson.baconators.recipe.DyeableRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;

/**
 * File created by Baijson.
 */
public class Registry {

    static public Item baconator;
    static public Item cluckinator;
    static public Item jerkynator;
    static public Item darkonator;

    static public SoundEvent toggleSound;
    static public SoundEvent sizzleSound;

    /**
     *
     */
    static public void init() {

        /**
         * Baconator
         */
        if (Settings.baItemEnabled) {
            baconator = Baconator.create(new ResourceLocation(Baconators.MODID, "baconator"), Settings.baMaxFoodCapacity, Settings.baAcceptedFoodList);
            GameRegistry.addRecipe(new ItemStack(baconator, 1),
                    "WRW", "IPI", "IPI",
                    'W', new ItemStack(Blocks.WOOL, 1, 6),
                    'R', new ItemStack(Items.REDSTONE, 1),
                    'I', new ItemStack(Items.IRON_INGOT, 1, OreDictionary.WILDCARD_VALUE),
                    'P', new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)
            );
            OreDictionary.registerOre("itemBaconator", baconator);
        }


        /**
         * Cluckinator
         *
         * https://www.twitch.tv/wyld
         */
        if (Settings.caItemEnabled) {
            cluckinator = Baconator.create(new ResourceLocation(Baconators.MODID, "cluckinator"), Settings.caMaxFoodCapacity, Settings.caAcceptedFoodList);
            GameRegistry.addRecipe(new ItemStack(cluckinator, 1),
                    "WRW", "IPI", "IPI",
                    'W', new ItemStack(Blocks.WOOL, 1, 0),
                    'R', new ItemStack(Items.REDSTONE, 1),
                    'I', new ItemStack(Items.IRON_INGOT, 1, OreDictionary.WILDCARD_VALUE),
                    'P', new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)
            );
            OreDictionary.registerOre("itemBaconator", cluckinator);
        }

        /**
         * Jerkynator
         */
        if (Settings.jaItemEnabled) {
            jerkynator = Baconator.create(new ResourceLocation(Baconators.MODID, "jerkynator"), Settings.jaMaxFoodCapacity, Settings.jaAcceptedFoodList);
            GameRegistry.addRecipe(new ItemStack(jerkynator, 1),
                    "WRW", "IPI", "IPI",
                    'W', new ItemStack(Blocks.WOOL, 1, 12),
                    'R', new ItemStack(Items.REDSTONE, 1),
                    'I', new ItemStack(Items.IRON_INGOT, 1, OreDictionary.WILDCARD_VALUE),
                    'P', new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)
            );
            OreDictionary.registerOre("itemBaconator", jerkynator);
        }

        /**
         * Darkonator
         *
         * https://www.twitch.tv/Darkosto
         */
        if (Settings.daItemEnabled) {
            darkonator = Baconator.create(new ResourceLocation(Baconators.MODID, "darkonator"), Settings.daMaxFoodCapacity, Settings.daAcceptedFoodList);
            GameRegistry.addRecipe(new ItemStack(darkonator, 1),
                    "WRW", "IPI", "IPI",
                    'W', new ItemStack(Blocks.WOOL, 1, 15),
                    'R', new ItemStack(Items.REDSTONE, 1),
                    'I', new ItemStack(Items.IRON_INGOT, 1, OreDictionary.WILDCARD_VALUE),
                    'P', new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)
            );
            OreDictionary.registerOre("itemBaconator", darkonator);
        }

        /**
         * Register DyeableRecipe
         */
        GameRegistry.addRecipe(new DyeableRecipe());
        RecipeSorter.register("baconators:dyeablerecipe", DyeableRecipe.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");

        /**
         * SoundEvents
         */
        toggleSound = GameRegistry.register(new SoundEvent(new ResourceLocation(Baconators.MODID, "toggleSound")).setRegistryName(
                new ResourceLocation(Baconators.MODID, "toggleSound")));
        sizzleSound = GameRegistry.register(new SoundEvent(new ResourceLocation(Baconators.MODID, "sizzleSound")).setRegistryName(
                new ResourceLocation(Baconators.MODID, "sizzleSound")));
    }
}
