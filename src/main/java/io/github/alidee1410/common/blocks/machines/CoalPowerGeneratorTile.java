package io.github.alidee1410.common.blocks.machines;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.github.alidee1410.AlisBeanMod;
import io.github.alidee1410.common.blocks.base.MachineTile;
import io.github.alidee1410.core.init.TileEntityTypeInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.ItemStackHandler;

public class CoalPowerGeneratorTile extends MachineTile {
	
	//Constants
	public static final int ENERGY_GENERATE = 100;
	public static final int TICKS_TO_GENERATE = 10;
	public static final List<Item> FUELS = Arrays.asList(Items.COAL, Items.CHARCOAL);
	public static final int MAX_OUTPUT = 1000;
	
	public CoalPowerGeneratorTile() {
		super(TileEntityTypeInit.COAL_POWER_GENERATOR_TILE.get());
		this.itemHandler = createItemHandler();
		this.energyStorage = createEnergyStorage();
		
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
		
		if (counter <= 0 && !energyStorage.isFull()) {
			ItemStack stackInSlot = itemHandler.getStackInSlot(0);
			if (FUELS.contains(stackInSlot.getItem())) {
				itemHandler.extractItem(0, 1, false);
				counter = TICKS_TO_GENERATE;
				markDirty();
			}
		}
		
		// Set block state to true or false based on counter
		BlockState blockState = world.getBlockState(pos);
		if (blockState.get(BlockStateProperties.LIT) != counter > 0) {
			world.setBlockState(pos, blockState.with(BlockStateProperties.LIT, counter > 0),
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
	
	public ItemStackHandler createItemHandler() {
		return new ItemStackHandler(1) {
			
			@Override
			public boolean isItemValid(int slot, ItemStack stack) { return FUELS.contains(stack.getItem()); }
			
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
	public Container createMenu(int windowId, PlayerInventory playerInv, PlayerEntity player) {
		return new CoalPowerGeneratorContainer(windowId, playerInv, this);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("screen." + AlisBeanMod.MOD_ID + ".coal_generator");
	}
}
