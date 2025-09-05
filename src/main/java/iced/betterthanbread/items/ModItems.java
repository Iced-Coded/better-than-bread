package iced.betterthanbread.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.*;
import iced.betterthanbread.BetterThanBread;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    //public static final Item FLOUR = registerItem("flour", new Item(new FabricItemSettings()));
    //public static final Item DOUGH = registerItem("dough", new Item(new FabricItemSettings().food(ModFoodComponent.DOUGH)));
    public static final Item CINNAMON = registerItem("cinnamon", new Item(new FabricItemSettings()));
    public static final Item RAW_CINNAMON_ROLL_DOUGH = registerItem("raw_cinnamon_roll_dough", new Item(new FabricItemSettings().food(ModFoodComponent.RAW_CINNAMON_ROLL_DOUGH)));
    public static final Item RAW_CINNAMON_ROLL = registerItem("raw_cinnamon_roll", new Item(new FabricItemSettings().food(ModFoodComponent.RAW_CINNAMON_ROLL)));
    public static final Item CINNAMON_ROLL = registerItem("cinnamon_roll", new Item(new FabricItemSettings().food(ModFoodComponent.CINNAMON_ROLL)));
    public static final Item GLAZED_CINNAMON_ROLL = registerItem("glazed_cinnamon_roll", new Item(new FabricItemSettings().food(ModFoodComponent.GLAZED_CINNAMON_ROLL)));

    private static void addItemsToFoodsItemGroup(FabricItemGroupEntries entries) {
        //entries.add(FLOUR);
        entries.add(CINNAMON);
        entries.add(RAW_CINNAMON_ROLL_DOUGH);
        entries.add(RAW_CINNAMON_ROLL);
        entries.add(CINNAMON_ROLL);
        entries.add(GLAZED_CINNAMON_ROLL);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BetterThanBread.MOD_ID, name), item);
    }

    public static void registerModItems() {
        BetterThanBread.LOGGER.info("Registering Mod Items for " + BetterThanBread.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemsToFoodsItemGroup);
    }
}
