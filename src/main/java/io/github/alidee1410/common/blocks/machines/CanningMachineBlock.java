package io.github.alidee1410.common.blocks.machines;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class CanningMachineBlock extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public CanningMachineBlock() {
		super(Properties.create(Material.IRON)
				.hardnessAndResistance(3.5f, 5f)
				.harvestTool(ToolType.PICKAXE)
				.harvestLevel(1)
				.sound(SoundType.METAL)
				.setRequiresTool()
				.setLightLevel(state -> state.get(BlockStateProperties.POWERED) ? 14 : 0));
		
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(BlockStateProperties.POWERED, false));
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) { return true; }
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) { return new CanningMachineTile(); }
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING, BlockStateProperties.POWERED);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		
		// Make sure world isn't remote (it is server-side)
		if (!worldIn.isRemote) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof CanningMachineTile) {
				
				// Handles opening gui client-side for us
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
			} else {
				throw new IllegalStateException("Named container provider is missing!");
			}
		}
		return ActionResultType.SUCCESS;
	}
}
