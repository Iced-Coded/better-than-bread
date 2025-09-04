package iced.betterthanbread.items;

import net.minecraft.item.FoodComponent;

public class ModFoodComponent {
    public static FoodComponent DOUGH = new FoodComponent.Builder().hunger(1).saturationModifier(0.25f).build();
}
