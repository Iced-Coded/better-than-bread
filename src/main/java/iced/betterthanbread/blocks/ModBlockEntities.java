package iced.betterthanbread.blocks;

import iced.betterthanbread.BetterThanBread;
import iced.betterthanbread.blocks.entity.ButterChurnBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<ButterChurnBlockEntity> BUTTER_CHURN;

    public static void registerModBlockEntities() {
        BUTTER_CHURN = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(BetterThanBread.MOD_ID, "buttter_churn"),
                FabricBlockEntityTypeBuilder.create(ButterChurnBlockEntity::new, ModBlocks.BUTTER_CHURN).build()
        );
    }
}