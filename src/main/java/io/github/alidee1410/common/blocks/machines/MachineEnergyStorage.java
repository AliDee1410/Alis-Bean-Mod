package io.github.alidee1410.common.blocks.machines;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class MachineEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT> {

	public MachineEnergyStorage(int capacity, int maxTransfer) {
		super(capacity, maxTransfer);
	}
	
	// This should be overridden in the tile entity class to mark it dirty
	protected void onEnergyChanged() {
		
	}
	
	public void setEnergy(int energy) {
        this.energy = energy;
        onEnergyChanged();
    }

    public void addEnergy(int energy) {
        this.energy += energy;
        if (this.energy > getMaxEnergyStored()) {
            this.energy = getMaxEnergyStored();
        }
        onEnergyChanged();
    }

    public void consumeEnergy(int energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
        onEnergyChanged();
    }
    
    public boolean isFull() {
    	return getEnergyStored() >= getMaxEnergyStored();
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("energy", getEnergyStored());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        setEnergy(nbt.getInt("energy"));
    }
}
