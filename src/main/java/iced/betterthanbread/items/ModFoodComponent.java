package iced.betterthanbread.items;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponent {
    //public static FoodComponent DOUGH = new FoodComponent.Builder().hunger(1).saturationModifier(0.25f).build();

    // Kids, don't eat raw dough
    public static FoodComponent RAW_CINNAMON_ROLL_DOUGH = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.35f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600), 1)
            .alwaysEdible()
            .build();

    public static FoodComponent RAW_CINNAMON_ROLL = new FoodComponent.Builder()
            .nutrition(1)
            .saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600), 1)
            .alwaysEdible()
            .build();

    public static FoodComponent CINNAMON_ROLL = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.5f)
            .build();

    public static FoodComponent GLAZED_CINNAMON_ROLL = new FoodComponent.Builder()
            .nutrition(5)
            .saturationModifier(0.55f)
            .build();

    public static FoodComponent BUTTER = new FoodComponent.Builder()
            .nutrition(1)
            .saturationModifier(0.5f)
            .build();

    public static FoodComponent SAUSAGE = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.5f)
            .build();

    public static FoodComponent RAW_SAUSAGE = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 600), 1)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 600), 1)
            .build();

    public static FoodComponent RAW_CROISSANT = new FoodComponent.Builder()
            .nutrition(1)
            .saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600), 1)
            .alwaysEdible()
            .build();

    public static FoodComponent CROISSANT = new FoodComponent.Builder()
            .nutrition(5)
            .saturationModifier(0.5f)
            .build();
}
