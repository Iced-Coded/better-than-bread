package iced.betterthanbread.items.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Random;

public class RollingPinItem extends Item {
    public RollingPinItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        ItemStack copy = stack.copy();
        copy.damage(1, null, e -> {});
        if (copy.getDamage() >= copy.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        return copy;
    }
}
