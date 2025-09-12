package iced.betterthanbread.blocks.custom;

import com.mojang.serialization.MapCodec;
import iced.betterthanbread.blocks.entity.ButterChurnBlockEntity;
import iced.betterthanbread.items.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ButterChurnBlock extends BlockWithEntity {

    private static final VoxelShape SHAPE = BlockWithEntity.createCuboidShape(3.0, 0.0, 3.0, 13.0, 13.0, 13.0);

    public ButterChurnBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;

        BlockEntity be = world.getBlockEntity(pos);
        if(!(be instanceof ButterChurnBlockEntity churn)) return ActionResult.PASS;

        ItemStack held = player.getStackInHand(hand);

        if (held.isOf(Items.MILK_BUCKET) && !churn.hasMilk()) {
            churn.insertMilk();
            if (!player.isCreative()) {
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
            }
            world.updateListeners(pos, state, state, Block.NOTIFY_ALL);
            return ActionResult.CONSUME;
        }

        if (held.isOf(Items.STICK) && churn.hasMilk()) {
            churn.churn();
            world.updateListeners(pos, state, state, Block.NOTIFY_ALL);
            return ActionResult.CONSUME;
        }

        if (held.isEmpty() && churn.isReady()) {
            player.giveItemStack(new ItemStack(ModItems.BUTTER));
            churn.reset();
            world.updateListeners(pos, state, state, Block.NOTIFY_ALL);
            return ActionResult.CONSUME;
        }

        return ActionResult.PASS;
    }

    @Nullable
    public ButterChurnBlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ButterChurnBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
