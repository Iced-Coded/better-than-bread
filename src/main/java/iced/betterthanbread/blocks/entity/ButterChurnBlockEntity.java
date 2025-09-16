package iced.betterthanbread.blocks.entity;

import iced.betterthanbread.blocks.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;

public class ButterChurnBlockEntity extends BlockEntity {
    private boolean hasMilk = false;
    private int churnProgress = 0;
    private final int churnNeeded = 5;
    private Item inputItem = Items.AIR; // Track the input item

    public ButterChurnBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BUTTER_CHURN, pos, state);
    }

    public boolean hasInput() {
        return this.hasMilk();
    }

    public void insertInput(Item item) {
        if (item == Items.MILK_BUCKET) {
            insertMilk();
        }
        // You can add support for other input items here later
    }

    public Item getInputItem() {
        return inputItem;
    }

    public void insertMilk() {
        hasMilk = true;
        inputItem = Items.MILK_BUCKET; // Set the input item
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
        inputItem = Items.AIR; // Reset input item
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