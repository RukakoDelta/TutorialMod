package net.rukakoray.tutorialmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.rukakoray.tutorialmod.entity.custom.CoinProjectileEntity;

public class CoinRenderer extends ThrownItemRenderer<CoinProjectileEntity> {
    public CoinRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(CoinProjectileEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        pPoseStack.scale(0.25f,0.25f,0.25f);

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
