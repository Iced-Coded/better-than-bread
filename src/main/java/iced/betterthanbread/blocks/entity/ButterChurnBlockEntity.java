package iced.betterthanbread.blocks.entity;

import iced.betterthanbread.blocks.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
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
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f
            );
        }

        syncAndDirty();
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

            syncAndDirty();
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

        syncAndDirty();
    }

    private void syncAndDirty() {
        markDirty();

        if (world != null && !world.isClient) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);

            if (world instanceof ServerWorld serverWorld) {
                serverWorld.getChunkManager().markForUpdate(pos);
            }
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.writeNbt(nbt, registries);
        nbt.putBoolean("HasMilk", hasMilk);
        nbt.putInt("ChurnProgress", churnProgress);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        this.hasMilk = nbt.getBoolean("HasMilk");
        this.churnProgress = nbt.getInt("ChurnProgress");
    }
}