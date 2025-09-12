package iced.betterthanbread.blocks.entity;

import iced.betterthanbread.BetterThanBreadClient;
import iced.betterthanbread.models.ChurnModel;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.InputStream;

public class ButterChurnRenderer implements BlockEntityRenderer<ButterChurnBlockEntity> {
    private final ChurnModel model;

    public ButterChurnRenderer(BlockEntityRendererFactory.Context context){
        this.model = new ChurnModel(context.getLayerModelPart(BetterThanBreadClient.BUTTER_CHURN_LAYER));
    }

    @Override
    public void render(ButterChurnBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        matrices.translate(0.5f, 1.5f, 0.5f); // Center in block space
        matrices.scale(1.0f, -1.0f, 1.0f);

        int blockLight = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos());

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(
                RenderLayer.getEntityCutoutNoCull(this.getTexture())
        );

        try {
            this.model.render(matrices, vertexConsumer, blockLight, overlay);
        } catch (Exception e) {
            e.printStackTrace();
        }

        matrices.pop();
    }

    public Identifier getTexture() {
        // Try loading the texture directly from the path
        try {
            InputStream stream = getClass().getResourceAsStream("/assets/betterthanbread/textures/entity/butter_churn.png");
            if (stream != null) {
                stream.close();
                var i = 0;
                if (i == 1) {

                }
                return Identifier.of("betterthanbread", "textures/entity/butter_churn.png");
            } else {
                System.out.println("Texture NOT found in resources");
                return Identifier.of("minecraft", "textures/block/oak_planks.png");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Identifier.of("minecraft", "textures/block/oak_planks.png");
        }
    }
}
