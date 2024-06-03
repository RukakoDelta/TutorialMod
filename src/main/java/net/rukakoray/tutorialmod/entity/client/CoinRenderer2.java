package net.rukakoray.tutorialmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.rukakoray.tutorialmod.entity.custom.CoinProjectileEntity;
import net.rukakoray.tutorialmod.entity.custom.CoinProjectileEntity2;
import net.rukakoray.tutorialmod.item.ModItems;

public class CoinRenderer2 extends ThrownItemRenderer<CoinProjectileEntity2> {
    public CoinRenderer2(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(CoinProjectileEntity2 pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        pPoseStack.scale(0.75f,0.75f,0.75f);

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
