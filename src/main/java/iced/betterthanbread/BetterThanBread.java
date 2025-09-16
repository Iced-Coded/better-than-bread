package iced.betterthanbread;

import iced.betterthanbread.blocks.ModBlockEntities;
import iced.betterthanbread.blocks.ModBlocks;
import iced.betterthanbread.recipes.ButterChurnRecipe;
import io.wispforest.owo.Owo;
import net.fabricmc.api.ModInitializer;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import iced.betterthanbread.items.ModItems;

public class BetterThanBread implements ModInitializer {
	public static final String MOD_ID = "betterthanbread";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final RecipeType<ButterChurnRecipe> BUTTER_CHURN = RecipeType.register("betterthanbread:butter_churn");

    public static final RecipeSerializer<ButterChurnRecipe> BUTTER_CHURN_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER,
            new Identifier(MOD_ID, "butter_churn"),
            new ButterChurnRecipe.Serializer()
    );

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
        ModItems.registerModItems();
        ModItems.GROUP.initialize();
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerModBlockEntities();
        LOGGER.info("Hello Fabric world!");
	}
}