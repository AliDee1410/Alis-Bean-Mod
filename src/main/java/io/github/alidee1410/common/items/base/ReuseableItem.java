package io.github.alidee1410.common.items.base;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ReuseableItem extends Item {
	
	// Code by BlakeBr0
	
	private final boolean damage; 
	
	public ReuseableItem(int uses, Properties properties) {
		super(properties.defaultMaxDamage(Math.max(uses - 1, 0)).setNoRepair());
		this.damage = uses > 0;
	}
	
	@Override
	public boolean hasContainerItem() {
		return true;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		ItemStack copy = stack.copy();
		copy.setCount(1);
		
		if (!this.damage) {
			return copy;
		}
		
		copy.setDamage(stack.getDamage() + 1);
		if (copy.getDamage() > stack.getMaxDamage()) {
			return ItemStack.EMPTY;
		}
		
		return copy;
	}
}
