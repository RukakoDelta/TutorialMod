package net.rukakoray.tutorialmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rukakoray.tutorialmod.TutorialMod;
import net.rukakoray.tutorialmod.entity.custom.CoinProjectileEntity;
import net.rukakoray.tutorialmod.entity.custom.CoinProjectileEntity2;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TutorialMod.MOD_ID);

    public static final RegistryObject<EntityType<CoinProjectileEntity>> COIN_PROJECTILE =
            ENTITY_TYPES.register("coin_projectile", () ->  EntityType.Builder.<CoinProjectileEntity>of(CoinProjectileEntity::new, MobCategory.MISC)
                    .sized(1.0f,1.0f).build("coin_projectile"));

    public static final RegistryObject<EntityType<CoinProjectileEntity2>> COIN_PROJECTILE2 =
            ENTITY_TYPES.register("coin_projectile2", () ->  EntityType.Builder.<CoinProjectileEntity2>of(CoinProjectileEntity2::new, MobCategory.MISC)
                    .sized(1.0f,1.0f).build("coin_projectile2"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
