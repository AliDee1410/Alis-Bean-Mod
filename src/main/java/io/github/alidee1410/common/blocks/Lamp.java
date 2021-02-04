package io.github.alidee1410.common.blocks;

import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class Lamp extends BaseHorizontalBlock {
	
	private static final VoxelShape SHAPE = Stream.of(
			Block.makeCuboidShape(4, 0, 4, 12, 1, 12),
			Block.makeCuboidShape(7, 1, 7, 9, 12, 9),
			Block.makeCuboidShape(4, 9, 4, 12, 11, 12),
			Block.makeCuboidShape(5, 11, 5, 11, 12, 11),
			Block.makeCuboidShape(6, 12, 6, 10, 13, 10))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();
	
	public Lamp(Properties properties) {
		super(properties);
		runCalculation(SHAPE);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES.get(this).get(state.get(HORIZONTAL_FACING));
	}
}
