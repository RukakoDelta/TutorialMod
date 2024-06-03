package net.rukakoray.tutorialmod.entity.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.rukakoray.tutorialmod.entity.ModEntities;
import net.rukakoray.tutorialmod.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CoinProjectileEntity extends ThrowableItemProjectile {

    private boolean hasBeenHit = false;
    private int damage = 5;
    private int damageMultiplier = 2;

    public CoinProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public CoinProjectileEntity(Level pLevel) {
        super(ModEntities.COIN_PROJECTILE.get(), pLevel);
    }

    public CoinProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.COIN_PROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    public void setNoGravity(boolean pNoGravity) {
        super.setNoGravity(pNoGravity);
    }

    @Override
    public void setInvulnerable(boolean pIsInvulnerable) {
        super.setInvulnerable(pIsInvulnerable);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.COIN.get();
    }

    protected void onHit(HitResult pResult) {

        if(!this.level().isClientSide()){
            this.level().broadcastEntityEvent(this,((byte) 3));
            this.discard();
        }
        //this.level().getNearestEntity(EntityTypeTags.)

        super.onHit(pResult);
    }

    public void hitBySharpshooterOrCoin(Player player, List<CoinProjectileEntity> liveCoins, int damageMultiplier){
        if(!hasBeenHit){
            this.damageMultiplier*=damageMultiplier;
            this.hasBeenHit = true;

            LivingEntity target = null;
            Vec3 currentPos = this.position();
            Vec3 targetPos;
            Vec3 newDirection;

            liveCoins.remove(this);
            if(liveCoins.isEmpty()){    //last coin, seek live target
                target = player.getLastHurtMob();
                if(target != null && target.isAlive()){
                    targetPos = target.position();
                    newDirection = currentPos.vectorTo(targetPos);
                    this.shoot(newDirection.x, newDirection.y,newDirection.z,7.0f,0.0f);
                }
                else{   //no target found, shoot downwards
                    this.shoot(0, -1,0,7.0f,0.0f);
                }
            }
            else{   // there are other coins, look for next one
                targetPos = liveCoins.getFirst().position();
                newDirection = currentPos.vectorTo(targetPos);
                this.shoot(newDirection.x, newDirection.y,newDirection.z,7.0f,0.0f);
                liveCoins.getFirst().hitBySharpshooterOrCoin(player,liveCoins,this.damageMultiplier);
            }
        }
    }


    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if(!this.level().isClientSide()){
            //this.level().broadcastEntityEvent(this,((byte) 3));
            pResult.getEntity().hurt(pResult.getEntity().damageSources().fall(),(this.damage * this.damageMultiplier));
        }
        this.discard();
    }

    @Override
    public ProjectileDeflection deflection(Projectile pProjectile) {
        return super.deflection(pProjectile);
    }

    @Override
    protected void onDeflection(@Nullable Entity pEntity, boolean p_335911_) {
        super.onDeflection(pEntity, p_335911_);
    }

    @Override
    protected ProjectileDeflection hitTargetOrDeflectSelf(HitResult pHitResult) {
        return super.hitTargetOrDeflectSelf(pHitResult);
    }
}
