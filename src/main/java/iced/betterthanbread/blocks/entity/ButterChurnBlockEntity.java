package iced.betterthanbread.blocks.entity;

import iced.betterthanbread.blocks.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;

public class ButterChurnBlockEntity extends BlockEntity {
    private boolean hasMilk = false;
    private int churnProgress = 0;
    private final int churnNeeded = 5;

    public ButterChurnBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BUTTER_CHURN, pos, state);
    }

    public void insertMilk() {
        hasMilk = true;
        churnProgress = 0;

        if (world != null) {
            world.playSound(
                    null,
                    pos,
                    SoundEvents.ITEM_BUCKET_EMPTY,
                    SoundCategory.BLOCKS
            );
        }

        markDirty();
    }

    public void churn() {
        if (world == null || world.isClient) return;

        if (hasMilk && churnProgress < churnNeeded) {
            churnProgress++;

            world.playSound(
                    null,
                    pos,
                    SoundEvents.BLOCK_BARREL_CLOSE,
                    SoundCategory.BLOCKS
            );

            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(
                        ParticleTypes.SPLASH,
                        pos.getX() + 0.5,
                        pos.getY() + 1,
                        pos.getZ() + 0.5,
                        6,
                        0.25,
                        0.15,
                        0.25,
                        0.02
                );
            }

            markDirty();
        }
    }

    public boolean hasMilk() {
        return hasMilk;
    }

    public boolean isReady() {
        return hasMilk && churnProgress >= churnNeeded;
    }

    public void reset() {
        hasMilk = false;
        churnProgress = 0;

        if (world != null) {
            world.playSound(
                    null,
                    pos,
                    SoundEvents.BLOCK_BARREL_OPEN,
                    SoundCategory.BLOCKS
            );
        }

        markDirty();
    }
}