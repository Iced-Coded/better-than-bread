package iced.betterthanbread.blocks;

import iced.betterthanbread.blocks.custom.ButterChurnBlock;
import iced.betterthanbread.blocks.custom.PorkpieBlock;
import iced.betterthanbread.items.ModItems;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static iced.betterthanbread.BetterThanBread.MOD_ID;

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

    public static final Block PORK_PIE = new PorkpieBlock(
            FabricBlockSettings.copyOf(Blocks.CAKE).nonOpaque()
    );

    public static final Item PORK_PIE_ITEM = new BlockItem(
            PORK_PIE,
            new OwoItemSettings()
                    .group(ModItems.GROUP)
                    .maxCount(1)
    );

    public static void registerModBlocks() {
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "butter_churn"), BUTTER_CHURN);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "butter_churn"), BUTTER_CHURN_ITEM);
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "pork_pie"), PORK_PIE);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "pork_pie"), PORK_PIE_ITEM);

        CompostingChanceRegistry.INSTANCE.add(PORK_PIE_ITEM, 1.0f);
    }
}