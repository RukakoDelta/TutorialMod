package net.rukakoray.tutorialmod.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.rukakoray.tutorialmod.entity.ModEntities;
import net.rukakoray.tutorialmod.item.ModItems;

public class CoinProjectileEntity extends ThrowableItemProjectile {
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
    protected Item getDefaultItem() {
        return ModItems.COIN.get();
    }

    @Override
    protected void onHit(HitResult pResult) {

        if(!this.level().isClientSide()){
            this.level().broadcastEntityEvent(this,((byte) 3));
            this.discard();
        }
        //this.level().getNearestEntity(EntityTypeTags.)

        super.onHit(pResult);
    }

    @Override
    public ProjectileDeflection deflection(Projectile pProjectile) {
        return super.deflection(pProjectile);
    }

}
