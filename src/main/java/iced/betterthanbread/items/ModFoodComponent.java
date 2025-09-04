package iced.betterthanbread.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponent {
    //public static FoodComponent DOUGH = new FoodComponent.Builder().hunger(1).saturationModifier(0.25f).build();

    // Kids, don't eat raw dough
    public static FoodComponent RAW_CINNAMON_ROLL_DOUGH = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600), 1)
            .alwaysEdible()
            .build();
}
