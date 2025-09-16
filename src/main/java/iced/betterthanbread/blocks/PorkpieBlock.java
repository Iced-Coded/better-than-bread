package iced.betterthanbread.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class PorkpieBlock extends Block {
    public static final int MAX_BITES = 4;
    public static final IntProperty BITES = IntProperty.of("bites", 0, MAX_BITES);
    private static final VoxelShape[] BITES_TO_SHAPE = new VoxelShape[] {
            Block.createCuboidShape(1, 0, 1, 15, 8, 15),
            Block.createCuboidShape(3, 0, 1, 15, 8, 15),
            Block.createCuboidShape(5, 0, 1, 15, 8, 15),
            Block.createCuboidShape(7, 0, 1, 15, 8, 15),
            Block.createCuboidShape(0, 0, 0, 16, 2, 16)
    };

    public PorkpieBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(BITES, 0));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BITES_TO_SHAPE[state.get(BITES)];
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        return tryEat(world, pos, state, player);
    }

    protected ActionResult tryEat(WorldAccess world, BlockPos pos,
                                  BlockState state, PlayerEntity player) {
        if (!player.canConsume(false)) return ActionResult.PASS;

        player.getHungerManager().add(4, 0.3f);
        int bites = state.get(BITES);
        if (bites < MAX_BITES) {
            world.setBlockState(pos, state.with(BITES, bites + 1), 3);
        } else {
            world.removeBlock(pos, false);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BITES);
    }
}
