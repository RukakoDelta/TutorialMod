package net.rukakoray.tutorialmod.entity.custom;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.rukakoray.tutorialmod.entity.ModEntities;
import net.rukakoray.tutorialmod.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class CoinProjectileEntity2 extends Fireball {
    private int coinDamage = 1;

    public CoinProjectileEntity2(EntityType<? extends Fireball> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public CoinProjectileEntity2(Level pLevel) {
        super(ModEntities.COIN_PROJECTILE2.get(), pLevel);
    }

    public CoinProjectileEntity2(Level pLevel, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, int damage) {
        super(ModEntities.COIN_PROJECTILE2.get(), pShooter, pOffsetX, pOffsetY, pOffsetZ, pLevel);
        this.coinDamage = damage;
    }

    /*public CoinProjectileEntity2(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.COIN_PROJECTILE2.get(), livingEntity, pLevel);
    }*/

    /*@Override
    public boolean isAttackable() {
        return true;
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public boolean skipAttackInteraction(Entity pEntity) {
        return false;
    }*/

    @Override
    public void setNoGravity(boolean pNoGravity) {
        super.setNoGravity(pNoGravity);
    }

    @Override
    public void setInvulnerable(boolean pIsInvulnerable) {
        super.setInvulnerable(pIsInvulnerable);
    }

    private ItemStack getDefaultItem() {
        return new ItemStack(ModItems.COIN.get());
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if(!this.level().isClientSide()){
            this.level().broadcastEntityEvent(this,((byte) 3));
            this.discard();
        }
        //this.level().getNearestEntity(EntityTypeTags.)
    }

    /*@Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if(!this.level().isClientSide()){
            //this.level().broadcastEntityEvent(this,((byte) 3));
            pResult.getEntity().hurt(pResult.getEntity().damageSources().fall(),coinDamage);
        }
        this.discard();
    }*/
}
