package iced.betterthanbread.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponent {
    //public static FoodComponent DOUGH = new FoodComponent.Builder().hunger(1).saturationModifier(0.25f).build();

    // Kids, don't eat raw dough
    public static FoodComponent RAW_CINNAMON_ROLL_DOUGH = new FoodComponent.Builder()
            .hunger(3)
            .saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600), 1)
            .alwaysEdible()
            .build();
    public static FoodComponent RAW_CINNAMON_ROLL = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600), 1)
            .alwaysEdible()
            .build();
    public static FoodComponent CINNAMON_ROLL = new FoodComponent.Builder()
            .hunger(4)
            .saturationModifier(2)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300), 1)
            .build();
    public static FoodComponent GLAZED_CINNAMON_ROLL = new FoodComponent.Builder()
            .hunger(5)
            .saturationModifier(2.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300), 1)
            .build();
    // TODO: make sausage in dough and a beef in dough
    public static FoodComponent SAUSAGE_IN_DOUGH = new FoodComponent.Builder()
            .hunger(5)
            .build();
    public static FoodComponent BEEF_IN_DOUGH = new FoodComponent.Builder()
            .hunger(5)
            .build();
}
