package iced.betterthanbread.items.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RollingPinItem extends Item {
    public RollingPinItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        ItemStack copy = stack.copy();

        copy.setDamage(copy.getDamage() + 1);

        if (copy.getDamage() >= copy.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        return copy;
    }

    @Override
    public boolean hasRecipeRemainder() {
        return true;
    }
}
