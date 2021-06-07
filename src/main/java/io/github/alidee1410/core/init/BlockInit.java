package io.github.alidee1410.core.init;

import io.github.alidee1410.AlisBeanMod;
import io.github.alidee1410.common.blocks.WildHaricotBeanBush;
import io.github.alidee1410.common.blocks.crops.CornCrop;
import io.github.alidee1410.common.blocks.crops.HaricotBeanCrop;
import io.github.alidee1410.common.blocks.crops.TomatoCrop;
import io.github.alidee1410.common.blocks.crops.WildHaricotBeanCrop;
import io.github.alidee1410.common.blocks.machines.CanningMachineBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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
	public static final RegistryObject<Block> TIN_BLOCK = BLOCKS.register("tin_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON)
					.hardnessAndResistance(3.5f, 5f)
					.harvestTool(ToolType.PICKAXE)
					.harvestLevel(1)
					.sound(SoundType.METAL)
					.setRequiresTool()));
	
	public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON)
					.hardnessAndResistance(5f, 6f)
					.harvestTool(ToolType.PICKAXE)
					.harvestLevel(2)
					.sound(SoundType.METAL)
					.setRequiresTool()));
	
	// Ores
	public static final RegistryObject<Block> TIN_ORE = BLOCKS.register("tin_ore",
			() -> new Block(AbstractBlock.Properties.from(Blocks.IRON_ORE)));
	
	// Crops
	public static final RegistryObject<Block> HARICOT_BEAN_CROP = BLOCKS.register("haricot_bean_crop",
			() -> new HaricotBeanCrop(AbstractBlock.Properties.from(Blocks.WHEAT)));
	
	public static final RegistryObject<Block> WILD_HARICOT_BEAN_CROP = BLOCKS.register("wild_haricot_bean_crop",
			() -> new WildHaricotBeanCrop(AbstractBlock.Properties.from(Blocks.WHEAT)));
	
	public static final RegistryObject<Block> TOMATO_CROP = BLOCKS.register("tomato_crop",
			() -> new TomatoCrop(AbstractBlock.Properties.from(Blocks.WHEAT)));
	
	public static final RegistryObject<Block> CORN_CROP = BLOCKS.register("corn_crop",
			() -> new CornCrop(AbstractBlock.Properties.from(Blocks.WHEAT)));
	
	// Bushes
	public static final RegistryObject<Block> WILD_HARICOT_BEAN_BUSH = BLOCKS.register("wild_haricot_bean_bush",
			() -> new WildHaricotBeanBush(AbstractBlock.Properties.create(Material.PLANTS)
					.zeroHardnessAndResistance()
					.doesNotBlockMovement()
					.tickRandomly()
					.sound(SoundType.PLANT)));
	
	public static final RegistryObject<Block> CANNING_MACHINE = BLOCKS.register("canning_machine",
			() -> new CanningMachineBlock(AbstractBlock.Properties.create(Material.IRON)
					.hardnessAndResistance(3.5f, 5f)
					.harvestTool(ToolType.PICKAXE)
					.harvestLevel(1)
					.sound(SoundType.METAL)
					.setRequiresTool()));
}
