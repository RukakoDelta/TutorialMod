package net.rukakoray.tutorialmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.extensions.IForgeEntity;
import net.rukakoray.tutorialmod.entity.custom.CoinProjectileEntity;

import java.util.List;

public class SharpshooterItem extends Item {
    public SharpshooterItem(Properties pProperties){
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP,
                SoundSource.NEUTRAL, 0.5F, 1.5F);
        if (!pLevel.isClientSide) {
            CoinProjectileEntity coin = new CoinProjectileEntity(pLevel, pPlayer);
            //coin.setItem(new ItemStack(ModItems.COIN.get()));
            coin.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 2.0F, 1.5F * pPlayer.getSpeed() + 1.0F, 1.0F);
            pLevel.addFreshEntity(coin);
        }

        /*
        if (!pLevel.isClientSide) {
            Snowball snowball = new Snowball(pLevel, pPlayer);
            //coinProjectile.setItem(new ItemStack(ModItems.COIN.get()));
            snowball.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 1.0F, 1.25F * pPlayer.getSpeed() + 1.0f, 1.0F);
            pLevel.addFreshEntity(snowball);
        }
        * */

        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        //itemStack.consume(1, pPlayer);
        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());



        //return super.use(pLevel, pPlayer, pUsedHand);
    }



    /*@Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(pIsSelected){
            pEntity.sendSystemMessage(Component.literal("InventoryTick"));
            //((Player)(pEntity)).entityInteractionRange() = (double)20;

        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }*/

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if(entity.distanceTo(player) <= 20){
            player.sendSystemMessage(Component.literal("leftClickEntity reached far"));
        }
        return super.onLeftClickEntity(stack, player, entity);
        //for coin mechanics
        //check if entity attacked is a coin
        //return true;
    }


    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.tutorialmod.sharpshooter.tooltip"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
