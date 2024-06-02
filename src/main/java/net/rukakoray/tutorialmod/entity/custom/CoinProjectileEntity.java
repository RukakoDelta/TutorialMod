package net.rukakoray.tutorialmod.entity.custom;

import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.rukakoray.tutorialmod.entity.ModEntities;

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
        return null;
    }

    @Override
    protected void onHit(HitResult pResult) {
        if(!this.level().isClientSide()){
            this.level().broadcastEntityEvent(this,((byte) 3));

        }

        //this.level().getNearestEntity(EntityTypeTags.)


        super.onHit(pResult);
    }
}
