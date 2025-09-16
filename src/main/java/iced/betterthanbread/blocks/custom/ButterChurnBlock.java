package iced.betterthanbread.blocks.custom;

import iced.betterthanbread.blocks.entity.ButterChurnBlockEntity;
import iced.betterthanbread.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.render.entity.model.EntityModelLayer;
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
import iced.betterthanbread.BetterThanBread;

public class ButterChurnBlock extends Block implements BlockEntityProvider {

    private static final VoxelShape SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 13.0, 13.0);

    public ButterChurnBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;

        var be = (ButterChurnBlockEntity) world.getBlockEntity(pos);
        if (be == null) return ActionResult.PASS;

        ItemStack held = player.getStackInHand(hand);

        // Get recipe manager and find matching recipe
        var recipeManager = world.getRecipeManager();
        var recipes = recipeManager.listAllOfType(BetterThanBread.BUTTER_CHURN);

        // Check if held item matches any recipe input
        for (var recipe : recipes) {
            if (recipe.getInput().test(held)) {
                if (!be.hasInput()) {
                    be.insertInput(held.getItem());
                    if (!player.isCreative()) {
                        held.decrement(1);
                    }
                    return ActionResult.CONSUME;
                }
            }
        }

        if (held.isOf(Items.STICK) && be.hasInput()) {
            be.churn();
            return ActionResult.CONSUME;
        }

        if (held.isEmpty() && be.isReady()) {
            // Get the output from the recipe
            for (var recipe : recipes) {
                if (recipe.getInput().test(new ItemStack(be.getInputItem()))) {
                    player.giveItemStack(recipe.getOutput().copy());
                    be.reset();
                    return ActionResult.SUCCESS;
                }
            }
        }

        return ActionResult.PASS;
    }

    @Nullable
    @Override
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
