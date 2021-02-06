package io.github.alidee1410.world;

import com.google.common.collect.ImmutableSet;

import io.github.alidee1410.common.blocks.WildHaricotBeanBush;
import io.github.alidee1410.core.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features.Placements;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class BeanModGeneration {

	public static void generateOres(final BiomeLoadingEvent event) {

		// Overworld Ores
		if (!event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND)) {
			generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
					BlockInit.TIN_ORE.get().getDefaultState(), 7, 0, 64, 15);
		}
	}

	public static void generateBushPatches(final BiomeLoadingEvent event) {

		// Haricot Bushes (Plains & Swamps Only)
		if (event.getCategory().equals(Biome.Category.PLAINS) || event.getCategory().equals(Biome.Category.SWAMP)) {
			generateBushPatch(event.getGeneration(), BlockInit.WILD_HARICOT_BEAN_BUSH.get().getDefaultState().with(WildHaricotBeanBush.AGE, 3), 46, 5, 3, 5,
					Blocks.GRASS_BLOCK.getBlock());
		}
	}

	private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state,
			int veinSize, int minHeight, int maxHeight, int maxPerChunk) {

		settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE.withConfiguration(new OreFeatureConfig(fillerType, state, veinSize))
						.withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
						.square().func_242731_b(maxPerChunk));
	}
	
	private static void generateBushPatch(BiomeGenerationSettingsBuilder settings, BlockState state, int tries,
			int spreadX, int spreadY, int spreadZ, Block spawnOn) {

		settings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(
						new SimpleBlockStateProvider(state),
						new SimpleBlockPlacer()))
						.tries(tries).xSpread(spreadX).ySpread(spreadY).zSpread(spreadZ)
						.whitelist(ImmutableSet.of(spawnOn)).func_227317_b_().build())
						.withPlacement(Placements.PATCH_PLACEMENT));
	}
}
