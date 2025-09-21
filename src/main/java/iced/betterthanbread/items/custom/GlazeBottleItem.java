package iced.betterthanbread.items.custom;

import iced.betterthanbread.BetterThanBread;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class GlazeBottleItem extends Item {
    public GlazeBottleItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = stack.copy();
        result.setDamage(result.getDamage() + 1);

        if (result.getDamage() >= result.getMaxDamage()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        }

        return result;
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        ItemStack copy = stack.copy();

        copy.setDamage(copy.getDamage() + 1);

        if (copy.getDamage() >= copy.getMaxDamage()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        }
        return copy;
    }

    @Override
    public boolean hasRecipeRemainder() {
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}
