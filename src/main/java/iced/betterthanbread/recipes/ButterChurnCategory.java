package iced.betterthanbread.recipes;

import iced.betterthanbread.BetterThanBread;
import iced.betterthanbread.blocks.ModBlocks;
import iced.betterthanbread.compat.jei.ButterChurnJeiPlugin;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class ButterChurnCategory implements IRecipeCategory<ButterChurnRecipe> {
    private final IDrawable background;
    @Nullable
    private final IDrawable icon;

    public ButterChurnCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(
                new Identifier(BetterThanBread.MOD_ID, "textures/gui/butter_churn_jei.png"),
                0, 0, 128, 64
        );
        this.icon = guiHelper.createDrawableItemStack(new ItemStack(ModBlocks.BUTTER_CHURN));
    }

    @Override
    public RecipeType<ButterChurnRecipe> getRecipeType() {
        return ButterChurnJeiPlugin.BUTTER_CHURN_TYPE;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("category.betterthanbread.butter_churn");
    }

    @Override
    @Deprecated
    public IDrawable getBackground() {
        return background;
    }

    @Override
    @Nullable
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ButterChurnRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 10, 20).addIngredients(recipe.getInput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 100, 20).addItemStack(recipe.getOutput());
    }
}