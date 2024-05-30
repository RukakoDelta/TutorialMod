package net.rukakoray.tutorialmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.rukakoray.tutorialmod.sound.ModSounds;

public class SoundBlock extends Block {
    public SoundBlock(Properties pProperties){
        super(pProperties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel,
                                               BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {

        pLevel.playSound(pPlayer, pPos, SoundEvent.createVariableRangeEvent(ModSounds.FROG_LAUGH.getId()), SoundSource.BLOCKS,1f,1f );

        return InteractionResult.SUCCESS;
    }
}
