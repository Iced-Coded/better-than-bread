package iced.betterthanbread.recipes;

import com.google.gson.JsonObject;
import iced.betterthanbread.BetterThanBread;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

public class ButterChurnRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final Ingredient input;
    private final ItemStack output;

    public ButterChurnRecipe(Identifier id, Ingredient input, ItemStack output) {
        this.id = id;
        this.input = input;
        this.output = output;
    }

    public Ingredient getInput() {
        return input;
    }

    @Override
    public boolean matches(SimpleInventory inv, World world) {
        return input.test(inv.getStack(0));
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output.copy();
    }

    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return BetterThanBread.BUTTER_CHURN_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return BetterThanBread.BUTTER_CHURN;
    }

    public static class Serializer implements RecipeSerializer<ButterChurnRecipe> {
        @Override
        public ButterChurnRecipe read(Identifier id, JsonObject json) {
            Ingredient input = Ingredient.fromJson(JsonHelper.getObject(json, "input"));
            ItemStack output = new ItemStack(JsonHelper.getItem(JsonHelper.getObject(json, "output"), "item"));
            return new ButterChurnRecipe(id, input, output);
        }

        @Override
        public ButterChurnRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient input = Ingredient.fromPacket(buf);
            ItemStack output = buf.readItemStack();
            return new ButterChurnRecipe(id, input, output);
        }

        @Override
        public void write(PacketByteBuf buf, ButterChurnRecipe recipe) {
            recipe.input.write(buf);
            buf.writeItemStack(recipe.output);
        }
    }
}