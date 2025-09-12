package iced.betterthanbread;

import iced.betterthanbread.blocks.ModBlockEntities;
import iced.betterthanbread.blocks.ModBlocks;
import iced.betterthanbread.blocks.entity.ButterChurnRenderer;
import iced.betterthanbread.models.ChurnModel;
import iced.betterthanbread.blocks.entity.ButterChurnRendererHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class BetterThanBreadClient implements ClientModInitializer {
    public static final EntityModelLayer BUTTER_CHURN_LAYER = new EntityModelLayer(Identifier.of("betterthanbread", "butter_churn"), "main");
    public static final Identifier BUTTER_CHURN_TEXTURE = Identifier.of("betterthanbread", "textures/entity/butter_churn.png");

    @Override
    public void onInitializeClient() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES)
                .registerReloadListener(new SimpleSynchronousResourceReloadListener() {
                    @Override
                    public Identifier getFabricId() {
                        return Identifier.of("betterthanbread", "item_renderer_reload");
                    }

                    @Override
                    public void reload(ResourceManager manager) {
                        ButterChurnRendererHelper.reload();
                    }
                });


        EntityModelLayerRegistry.registerModelLayer(BUTTER_CHURN_LAYER, ChurnModel::getTexturedModelData);

        BlockEntityRendererRegistry.register(ModBlockEntities.BUTTER_CHURN, ButterChurnRenderer::new);

        BuiltinItemRendererRegistry.INSTANCE.register(ModBlocks.BUTTER_CHURN.asItem(),
                (ItemStack stack, net.minecraft.client.render.model.json.ModelTransformationMode mode,
                MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                int light, int overlay) -> {

                    ChurnModel model = new ChurnModel(
                            MinecraftClient.getInstance().getEntityModelLoader().getModelPart(BUTTER_CHURN_LAYER)
                    );

                    matrices.push();

                    switch (mode) {
                        case GUI:
                            matrices.translate(0.5, 1.3, 0.0);
                            matrices.scale(0.8f, 0.8f, 0.8f);
                            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(25f));
                            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(210f));
                            break;
                        case FIRST_PERSON_LEFT_HAND:
                        case FIRST_PERSON_RIGHT_HAND:
                        case THIRD_PERSON_LEFT_HAND:
                        case THIRD_PERSON_RIGHT_HAND:
                            matrices.translate(0.5, 1.25, 0.5);
                            matrices.scale(0.75f, 0.75f, 0.75f);
                            break;
                        default:
                            matrices.translate(0.5, 1.1, 0.5);
                            matrices.scale(0.5f, 0.5f, 0.5f);
                            break;
                    }


                    matrices.scale(1.0f, -1.0f, 1.0f);

                    VertexConsumer vc = vertexConsumers.getBuffer(
                            RenderLayer.getEntityCutoutNoCull(BUTTER_CHURN_TEXTURE)
                    );

                    model.render(matrices, vc, light, overlay);

                    matrices.pop();

                });
    }
}
