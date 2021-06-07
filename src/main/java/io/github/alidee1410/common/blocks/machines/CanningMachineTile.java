package io.github.alidee1410.common.blocks.machines;

import io.github.alidee1410.core.init.TileEntityTypeInit;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CanningMachineTile extends TileEntity implements ITickableTileEntity {
	
	private ItemStackHandler handler;
	
	public CanningMachineTile() {
		super(TileEntityTypeInit.CANNING_MACHINE.get());
	}

	@Override
	public void tick() {
	}
	
	public ItemStackHandler getHandler() {
		if (handler == null) {
			handler = new ItemStackHandler(1);
		}
		return handler;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return LazyOptional.of(() -> (T) getHandler());
		}
		return super.getCapability(cap, side);
	}
	
	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		CompoundNBT invNBT = nbt.getCompound("inv");
		getHandler().deserializeNBT(invNBT);
		super.read(state, nbt);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT nbt) {
		CompoundNBT invNBT = getHandler().serializeNBT();
		nbt.put("inv", invNBT);
		return super.write(nbt);
	}
}
