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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.extensions.IForgeEntity;
import net.rukakoray.tutorialmod.entity.custom.CoinProjectileEntity;
import net.rukakoray.tutorialmod.entity.custom.CoinProjectileEntity2;
import net.rukakoray.tutorialmod.item.ModItems;


import java.util.ArrayList;
import java.util.List;

public class SharpshooterItem extends Item {

    public List<CoinProjectileEntity> liveCoins;

    public SharpshooterItem(Properties pProperties){
        super(pProperties);
        this.liveCoins = new ArrayList<>();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP,
                SoundSource.NEUTRAL, 0.5f, 1.7f);
        if (!pLevel.isClientSide) {

            CoinProjectileEntity coin = new CoinProjectileEntity(pLevel, pPlayer);
            //coin.setItem(new ItemStack(ModItems.COIN.get()));
            //CoinProjectileEntity2 coin = new CoinProjectileEntity2(pLevel, pPlayer,0f,0f,0f,5);
            //coin.setItem(new ItemStack(ModItems.COIN.get()));
            coin.shootFromRotation(pPlayer, pPlayer.getXRot()-15.0f, pPlayer.getYRot(),
                    0.0f, /*0.5f*pPlayer.getSpeed() + 0.5f*/ 0f, 0.0f);
            coin.setNoGravity(true);
            pLevel.addFreshEntity(coin);
            this.liveCoins.add(coin);

            pPlayer.sendSystemMessage(Component.literal("liveCoins list has " + liveCoins.size()));
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

        if(entity.getType().toString().equals("entity.tutorialmod.coin_projectile")){
            player.sendSystemMessage(Component.literal("hitCoin"));
            CoinProjectileEntity coin = (CoinProjectileEntity) entity;
            coin.hitBySharpshooterOrCoin(player,this.liveCoins,1);
            this.liveCoins.clear();
        }
        else{
            player.sendSystemMessage(Component.literal("storedTarget"));
        }
        player.sendSystemMessage(Component.literal(entity.getType().toString()));
        return super.onLeftClickEntity(stack, player, entity);
    }

    /*@Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity player) {
        player.sendSystemMessage(Component.literal("swing"));
        return super.onEntitySwing(stack, player);
    }*/

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.tutorialmod.sharpshooter.tooltip"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
