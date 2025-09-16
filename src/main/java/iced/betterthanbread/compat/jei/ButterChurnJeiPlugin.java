package iced.betterthanbread.compat.jei;

import iced.betterthanbread.BetterThanBread;
import iced.betterthanbread.blocks.ModBlocks;
import iced.betterthanbread.recipes.ButterChurnCategory;
import iced.betterthanbread.recipes.ButterChurnRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;

import java.util.List;

@JeiPlugin
public class ButterChurnJeiPlugin implements IModPlugin {
    public static final RecipeType<ButterChurnRecipe> BUTTER_CHURN_TYPE =
            RecipeType.create(BetterThanBread.MOD_ID, "butter_churn", ButterChurnRecipe.class);

    private static final Identifier PLUGIN_ID = new Identifier(BetterThanBread.MOD_ID, "jei_plugin");

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new ButterChurnCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public Identifier getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        // Safely get recipes without relying on the world
        RecipeManager recipeManager = null;
        MinecraftClient client = MinecraftClient.getInstance();

        if (client != null && client.world != null) {
            recipeManager = client.world.getRecipeManager();
        }

        if (recipeManager != null) {
            List<ButterChurnRecipe> recipes = recipeManager.listAllOfType(BetterThanBread.BUTTER_CHURN);
            registration.addRecipes(BUTTER_CHURN_TYPE, recipes);
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BUTTER_CHURN.asItem()), BUTTER_CHURN_TYPE);
    }
}