package io.github.alidee1410.common.blocks.machines;

import io.github.alidee1410.core.init.BlockInit;
import io.github.alidee1410.core.init.ContainerTypeInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class CoalPowerGeneratorContainer extends Container {

	private TileEntity tileEntity;
	private PlayerEntity player;
	private IItemHandler playerInv;
	
	public CoalPowerGeneratorContainer(int windowId, PlayerInventory playerInv, CoalPowerGeneratorTile tileEntity) {
		super(ContainerTypeInit.COAL_POWER_GENERATOR_CONTAINER.get(), windowId);
		
		this.tileEntity = tileEntity;
		this.player = playerInv.player;
		this.playerInv = new InvWrapper(playerInv);
		
		// Coal Generator Inventory Slots
		if (tileEntity != null) {
			tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
				addSlot(new SlotItemHandler(handler, 0, 64, 24));
			});
		}
		
		layoutPlayerInventorySlots(10, 70);
		trackPower();
	}
	
	public CoalPowerGeneratorContainer(int windowId, PlayerInventory playerInv, PacketBuffer data) {
		this(windowId, playerInv, (CoalPowerGeneratorTile) playerInv.player.world.getTileEntity(data.readBlockPos()));
	}
	
	private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Main inventory
        addSlotBox(playerInv, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInv, 0, leftCol, topRow, 9, 18);
    }
	
	private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }
	
	private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

	private void trackPower() {
		
		trackInt(new IntReferenceHolder() {
			
			@Override
			public int get() {
				return getEnergy() & 0xffff;
			}
			
			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(handler -> {
                    int energyStored = handler.getEnergyStored() & 0xffff0000;
                    ((MachineEnergyStorage)handler).setEnergy(energyStored + (value & 0xffff));
                });
			}
			
		});
		
		trackInt(new IntReferenceHolder() {
			
			@Override
            public int get() {
                return (getEnergy() >> 16) & 0xffff;
            }
			
			@Override
            public void set(int value) {
                tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(handler -> {
                    int energyStored = handler.getEnergyStored() & 0x0000ffff;
                    ((MachineEnergyStorage)handler).setEnergy(energyStored | (value << 16));
                });
            }
		});
	}
	
	public int getEnergy() {
		return tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), player, BlockInit.COAL_POWER_GENERATOR.get());
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack stackAfter = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack stackBefore = slot.getStack();
			stackAfter = stackBefore.copy();
			if (index == 0) { // If slot is fuel slot
				if (!this.mergeItemStack(stackBefore, 1, 37, true)) { // Try to merge fuel into player inv
					return ItemStack.EMPTY;
				}
				slot.onSlotChange(stackBefore, stackAfter);
			} else { // If slot is not fuel slot (slot has to be in player inv)
				if (CoalPowerGeneratorTile.FUELS.contains(stackBefore.getItem())) { // If item in slot is valid fuel item
					if (!this.mergeItemStack(stackBefore, 0, 1, false)) { // Try to merge stack into fuel slot
						return ItemStack.EMPTY;
					}
				} else if (index < 28) { // If item is not valid fuel item and is not in hot bar
					if (!this.mergeItemStack(stackBefore, 28, 37, false)) { // Try to merge stack into hot bar
						return ItemStack.EMPTY;
					}
					
				// If item is not valid fuel item and is in hot bar, try to merge stack into main inv
				} else if (index < 37 && !this.mergeItemStack(stackBefore, 1, 28, false)) {
					return ItemStack.EMPTY;
				}
			}
			
			// Merge was successful!
			
			if (stackBefore.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
			
			if (stackBefore.getCount() == stackAfter.getCount()) {
				return ItemStack.EMPTY;
			}
			
			slot.onTake(playerIn, stackBefore);
		}
		
		return stackAfter;
	}

}
