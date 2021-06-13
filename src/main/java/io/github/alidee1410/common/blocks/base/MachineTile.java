package io.github.alidee1410.common.blocks.base;

import io.github.alidee1410.common.blocks.machines.MachineEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class MachineTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider  {
	
	// Handlers
	protected ItemStackHandler itemHandler;
	protected MachineEnergyStorage energyStorage;
	
	// Capabilities
	protected LazyOptional<IItemHandler> itemHandlerCap = LazyOptional.of(() -> itemHandler);
	protected LazyOptional<IEnergyStorage> energyStorageCap = LazyOptional.of(() -> energyStorage);
	
	protected int counter;
	
	public MachineTile(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		itemHandler.deserializeNBT(nbt.getCompound("inv"));
		energyStorage.deserializeNBT(nbt.getCompound("energy"));
		counter = nbt.getInt("counter");
		
		super.read(state, nbt);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT nbt) {
		nbt.put("inv", itemHandler.serializeNBT());
		nbt.put("energy", energyStorage.serializeNBT());
		nbt.putInt("counter", counter);
		
		return super.write(nbt);
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)) {
			return itemHandlerCap.cast();
		}
		if (cap.equals(CapabilityEnergy.ENERGY)) {
			return energyStorageCap.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
		return null;
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}

	@Override
	public void tick() {
	}
}
