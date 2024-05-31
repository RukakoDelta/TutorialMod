package net.rukakoray.tutorialmod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.rukakoray.tutorialmod.TutorialMod;

public class ModTags {
    //block tags
    public static class Blocks{

        public static final TagKey<Block> METAL_DETECTOR_VALUABLES = tag("metal_detector_valuables");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(TutorialMod.MOD_ID,name));
        }
    }
    //item tags
    public static class Items{

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(TutorialMod.MOD_ID,name));
        }
    }
}
