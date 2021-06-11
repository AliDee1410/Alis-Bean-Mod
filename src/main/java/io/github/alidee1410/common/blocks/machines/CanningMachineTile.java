package io.github.alidee1410.common.blocks.machines;

import java.util.concurrent.atomic.AtomicInteger;

import io.github.alidee1410.AlisBeanMod;
import io.github.alidee1410.core.init.TileEntityTypeInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CanningMachineTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
	
	//Constants
	public static final int ENERGY_GENERATE = 100;
	public static final int TICKS_TO_GENERATE = 100;
	public static final Item FUEL = Items.EMERALD;
	public static final int MAX_OUTPUT = 1000;
	
	// Handlers
	private ItemStackHandler itemHandler = createItemHandler();
	private MachineEnergyStorage energyStorage = createEnergyStorage();
	
	// Capabilities
	private LazyOptional<IItemHandler> itemHandlerCap = LazyOptional.of(() -> itemHandler);
	private LazyOptional<IEnergyStorage> energyStorageCap = LazyOptional.of(() -> energyStorage);
	
	private int counter;
	
	public CanningMachineTile() {
		super(TileEntityTypeInit.CANNING_MACHINE.get());
	}

	@Override
	public void tick() {
		if (world.isRemote) { return; }
		
		if (counter > 0) {
			counter--;
			if (counter <= 0) {
				energyStorage.addEnergy(ENERGY_GENERATE);
			}
			markDirty();
		}
		
		if (counter <= 0) {
			ItemStack itemInSlot = itemHandler.getStackInSlot(0);
			if (itemInSlot.getItem() == FUEL) {
				itemHandler.extractItem(0, 1, false);
				counter = TICKS_TO_GENERATE;
				markDirty();
			}
		}
		
		// Set block state to true or false based on counter
		BlockState blockState = world.getBlockState(pos);
		if (blockState.get(BlockStateProperties.POWERED) != counter > 0) {
			world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, counter > 0),
					Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
		}
		
		outputPower();
	}
	
	private void outputPower() {
		AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
		if (capacity.get() > 0) {
			for (Direction direction : Direction.values()) {
				TileEntity te = world.getTileEntity(pos.offset(direction));
				if (te != null) {
					boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
						if (handler.canReceive()) {
							int received = handler.receiveEnergy(Math.min(capacity.get(), MAX_OUTPUT), false);
							capacity.addAndGet(-received);
							energyStorage.consumeEnergy(received);
							markDirty();
							return capacity.get() > 0;
						} else {
							return true;
						}
					}).orElse(true);
					if (!doContinue) {
						return;
					}
				}
			}
		}
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
	
	public ItemStackHandler createItemHandler() {
		return new ItemStackHandler(1) {
			
			@Override
			public boolean isItemValid(int slot, ItemStack stack) { return stack.getItem() == FUEL; }
			
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
			}
			
			@Override
			public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
				if (!isItemValid(slot, stack)) {
					return stack;
				}
				return super.insertItem(slot, stack, simulate);
			}
		};
	}
	
	public MachineEnergyStorage createEnergyStorage() {
		return new MachineEnergyStorage(5000, 0) {
			
			@Override
			protected void onEnergyChanged() {
				markDirty();
			}
		};
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
	public Container createMenu(int windowId, PlayerInventory playerInv, PlayerEntity player) {
		System.out.println("Creating menu for ali");
		return new CanningMachineContainer(windowId, playerInv, this);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("screen." + AlisBeanMod.MOD_ID + ".canning_machine");
	}
}
