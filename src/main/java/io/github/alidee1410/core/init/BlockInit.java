package io.github.alidee1410.core.init;

import io.github.alidee1410.AlisBeanMod;
import io.github.alidee1410.common.blocks.Lamp;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

	// Create block registry
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AlisBeanMod.MOD_ID);
	
	// Blocks
	public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON)
					.hardnessAndResistance(5f, 6f)
					.harvestTool(ToolType.PICKAXE)
					.harvestLevel(2)
					.sound(SoundType.METAL)
					.setRequiresTool()));
	
	// Custom Models
	public static final RegistryObject<Lamp> LAMP = BLOCKS.register("lamp",
			() -> new Lamp(AbstractBlock.Properties.create(Material.WOOD)
					.hardnessAndResistance(2f, 3f)
					.harvestTool(ToolType.AXE)
					.harvestLevel(0)
					.sound(SoundType.WOOD)
					.setLightLevel((state) -> { return 15; })
					.setRequiresTool()));
}
