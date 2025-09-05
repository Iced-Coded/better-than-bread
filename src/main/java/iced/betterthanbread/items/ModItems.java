package iced.betterthanbread.items;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import iced.betterthanbread.BetterThanBread;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.Icon;

public class ModItems {
    // For the mod's ItemGroup
    public static final OwoItemGroup GROUP = OwoItemGroup
            .builder(new Identifier(BetterThanBread.MOD_ID, "betterthanbread"), () -> Icon.of(itemGroupIcon()))
            .build();

    // Lazily load the cinnamon texture
    private static Item itemGroupIcon() {
        return CINNAMON;
    }

    public static final Item CINNAMON = registerItem("cinnamon", new Item(new OwoItemSettings()
            .group(ModItems.GROUP)
    ));
    public static final Item RAW_CINNAMON_ROLL_DOUGH = registerItem("raw_cinnamon_roll_dough", new Item(new OwoItemSettings()
            .food(ModFoodComponent.RAW_CINNAMON_ROLL_DOUGH)
            .group(ModItems.GROUP)
    ));
    public static final Item RAW_CINNAMON_ROLL = registerItem("raw_cinnamon_roll", new Item(new OwoItemSettings()
            .food(ModFoodComponent.RAW_CINNAMON_ROLL)
            .group(ModItems.GROUP)
    ));
    public static final Item CINNAMON_ROLL = registerItem("cinnamon_roll", new Item(new OwoItemSettings()
            .food(ModFoodComponent.CINNAMON_ROLL)
            .group(ModItems.GROUP)
    ));
    public static final Item GLAZED_CINNAMON_ROLL = registerItem("glazed_cinnamon_roll", new Item(new OwoItemSettings()
            .food(ModFoodComponent.GLAZED_CINNAMON_ROLL)
            .group(ModItems.GROUP)
    ));
    public static final Item SAUSAGE_IN_DOUGH = registerItem("sausage_in_dough", new Item(new OwoItemSettings()
            .food(ModFoodComponent.SAUSAGE_IN_DOUGH)
            .group(ModItems.GROUP)
    ));
    public static final Item BEEF_IN_DOUGH = registerItem("beef_in_dough", new Item(new OwoItemSettings()
            .food(ModFoodComponent.BEEF_IN_DOUGH)
            .group(ModItems.GROUP)
    ));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BetterThanBread.MOD_ID, name), item);
    }

    public static void registerModItems() {
        BetterThanBread.LOGGER.info("Registering Mod Items for " + BetterThanBread.MOD_ID);
    }
}
