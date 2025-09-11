package iced.betterthanbread.blocks.entity;

import iced.betterthanbread.BetterThanBreadClient;
import iced.betterthanbread.models.ChurnModel;
import net.minecraft.client.MinecraftClient;

public class ButterChurnRendererHelper {
    private static ChurnModel model;

    public static ChurnModel getModel() {
        if (model == null) {
            reload();
        }
        return model;
    }

    public static void reload() {
        model = new ChurnModel(
                MinecraftClient.getInstance().getEntityModelLoader()
                        .getModelPart(BetterThanBreadClient.BUTTER_CHURN_LAYER)
        );
    }
}
