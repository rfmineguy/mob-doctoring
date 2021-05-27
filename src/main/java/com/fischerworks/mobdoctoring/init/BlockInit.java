package com.fischerworks.mobdoctoring.init;

import com.fischerworks.mobdoctoring.MobDoctoring;
import com.fischerworks.mobdoctoring.blocks.CatalysteOreBlock;
import com.fischerworks.mobdoctoring.blocks.centrifuge.CentrifugeBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS_REG = DeferredRegister.create(ForgeRegistries.BLOCKS, MobDoctoring.MOD_ID);

    public static final RegistryObject<Block> CATALYST_ORE = BLOCKS_REG.register("catalyst_ore", () -> new CatalysteOreBlock(
            AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()
                    .sound(SoundType.STONE)
                    .hardnessAndResistance(3.0F, 3.0F)
            ));

    public static final RegistryObject<Block> MOB_CENTRIFUGE = BLOCKS_REG.register("block_mob_centrifuge    ", CentrifugeBlock::new);
}
