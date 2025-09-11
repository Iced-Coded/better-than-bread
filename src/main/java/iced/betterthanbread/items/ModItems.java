package iced.betterthanbread.items;
import iced.betterthanbread.BetterThanBread;

import iced.betterthanbread.items.custom.RollingPinItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.Icon;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;

public class ModItems {
    // For the mod's ItemGroup
    public static final OwoItemGroup GROUP = OwoItemGroup
            .builder(new Identifier(BetterThanBread.MOD_ID, "betterthanbread"), () -> Icon.of(itemGroupIcon()))
            .build();

    // Lazily load the cinnamon texture
    private static Item itemGroupIcon() {
        return CINNAMON;
    }

    public static final Item CINNAMON = simpleItem("cinnamon", 0.1f);
    public static final Item SAUSAGE = foodItem("sausage", ModFoodComponent.SAUSAGE, 0.45f);
    public static final Item RAW_SAUSAGE = foodItem("raw_sausage", ModFoodComponent.RAW_SAUSAGE, 0.35f);

    public static final Item RAW_CINNAMON_ROLL_DOUGH = foodItem("raw_cinnamon_roll_dough", ModFoodComponent.RAW_CINNAMON_ROLL_DOUGH, 0.3f);
    public static final Item RAW_CINNAMON_ROLL = foodItem("raw_cinnamon_roll", ModFoodComponent.RAW_CINNAMON_ROLL, 0.3f);
    public static final Item CINNAMON_ROLL = foodItem("cinnamon_roll", ModFoodComponent.CINNAMON_ROLL, 0.45f);
    public static final Item GLAZED_CINNAMON_ROLL = foodItem("glazed_cinnamon_roll", ModFoodComponent.GLAZED_CINNAMON_ROLL, 0.5f);

    public static final Item ROLLING_PIN = registerItem("rolling_pin",
            new RollingPinItem(new OwoItemSettings()
                    .group(ModItems.GROUP)
                    .maxCount(1)
                    .maxDamage(64)
            ));

    public static final Item BUTTER = foodItem("butter", ModFoodComponent.BUTTER, 0.1f);

    //public static final Item SAUSAGE_IN_DOUGH = foodItem("sausage_in_dough", ModFoodComponent.MEAT_IN_DOUGH, 0.5f);
    //public static final Item BEEF_IN_DOUGH = foodItem("beef_in_dough", ModFoodComponent.MEAT_IN_DOUGH, 0.5f);
    //public static final Item RAW_SAUSAGE_IN_DOUGH = foodItem("raw_sausage_in_dough", ModFoodComponent.RAW_MEAT_IN_DOUGH, 0.35f);
    //public static final Item RAW_BEEF_IN_DOUGH = foodItem("raw_beef_in_dough", ModFoodComponent.RAW_MEAT_IN_DOUGH, 0.35f);

    // Mincer Parts
    public static final Item BLADE = simpleItem("blade", 0);

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BetterThanBread.MOD_ID, name), item);
    }

    private static Item foodItem(String name, FoodComponent food, float composting) {
        Item item = registerItem(name, new Item(new OwoItemSettings().food(food)
                .group(ModItems.GROUP)
        ));
        if (composting > 0) {
            CompostingChanceRegistry.INSTANCE.add(item, composting);
        }
        return item;
    }

    private static Item simpleItem(String name, float composting) {
        Item item = registerItem(name, new Item(new OwoItemSettings()
                .group(ModItems.GROUP)
        ));
        if (composting > 0) {
            CompostingChanceRegistry.INSTANCE.add(item, composting);
        }
        return item;
    }

    public static void registerModItems() {
        BetterThanBread.LOGGER.info("Registering Mod Items for " + BetterThanBread.MOD_ID);
    }
}
