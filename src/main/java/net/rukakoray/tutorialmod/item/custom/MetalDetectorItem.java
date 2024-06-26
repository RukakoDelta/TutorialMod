package net.rukakoray.tutorialmod.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.rukakoray.tutorialmod.util.ModTags;

import java.util.List;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()){
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++){
                BlockState state =  pContext.getLevel().getBlockState(positionClicked.below(i));
                
                if(isValuableBlock(state)){
                    outputValuableCoordinates(positionClicked.below(i), player, state.getBlock());
                    foundBlock = true;
                    break;
                }
            }

            if(!foundBlock){
                player.sendSystemMessage(Component.literal("No valuables found"));
            }
            
            pContext.getItemInHand().hurtAndBreak(1,pContext.getPlayer(), pContext.getPlayer().getSlotForHand(pContext.getHand()));

        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.tutorialmod.metal_detector.tooltip"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(   // output into player's chat
                Component.literal("Found " + I18n.get(block.getDescriptionId()) // translate name of block
                + " at (" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")")
        );
    }

    private boolean isValuableBlock(BlockState state) {
        //return state.is(Blocks.IRON_ORE) || state.is(Blocks.GOLD_ORE) ||state.is(Blocks.DIAMOND_ORE);
        return state.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }
}
