package iced.betterthanbread.blocks;

import iced.betterthanbread.BetterThanBread;
import iced.betterthanbread.blocks.custom.ButterChurnBlock;
import iced.betterthanbread.items.ModItems;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block BUTTER_CHURN = new ButterChurnBlock(
            FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).nonOpaque()
    );

    // create a public Item reference for the BlockItem
    public static final Item BUTTER_CHURN_ITEM = new BlockItem(
            BUTTER_CHURN,
            new OwoItemSettings()
                    .group(ModItems.GROUP) // your item group
                    .maxCount(1)           // limit stack size to 1
    );

    public static void registerModBlocks() {
        Registry.register(Registries.BLOCK, new Identifier(BetterThanBread.MOD_ID, "butter_churn"), BUTTER_CHURN);
        Registry.register(Registries.ITEM, new Identifier(BetterThanBread.MOD_ID, "butter_churn"), BUTTER_CHURN_ITEM);
    }
}