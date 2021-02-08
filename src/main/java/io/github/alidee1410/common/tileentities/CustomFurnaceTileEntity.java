package io.github.alidee1410.common.tileentities;

import javax.annotation.Nullable;

import io.github.alidee1410.AlisBeanMod;
import io.github.alidee1410.core.init.TileEntityTypeInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;

public class CustomFurnaceTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

	private ITextComponent customName;
	
	public CustomFurnaceTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	/*
	public CustomFurnaceTileEntity() {
		this(TileEntityTypeInit.CUSTOM_FURNACE.get());
	}*/
	
	@Override
	public Container createMenu(final int windowID, final PlayerInventory playerInv, final PlayerEntity playerIn) {
		return null;//new CustomFurnaceContainer(windowID, playerInv, this);
	}
	
	@Override
	public void tick() {
		boolean dirty = false;
		
		if(this.world != null && !this.world.isRemote) {
			if(this.world.isBlockPowered(this.getPos())) {
				
			}
		}
	}
	
	public void setCustomName(ITextComponent name) {
		this.customName = name;
	}
	
	public ITextComponent getName() {
		return this.customName != null ? this.customName : this.getDefaultName();
	}
	
	private ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + AlisBeanMod.MOD_ID + ".custom_furnace");
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return this.getName();
	}
	
	@Nullable
	public ITextComponent getCustomName() {
		return this.customName;
	}
	
	public void read(BlockState state, CompoundNBT compound) {
		super.read(state, compound);
		if(compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
			this.customName = ITextComponent.Serializer.getComponentFromJson(compound.getString("CustomName"));
		}
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		if(this.customName != null) {
			compound.putString("CustomName", ITextComponent.Serializer.toJson(customName));
		}
		return compound;
	}
}
