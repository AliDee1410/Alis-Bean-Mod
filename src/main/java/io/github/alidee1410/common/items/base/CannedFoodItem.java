package io.github.alidee1410.common.items.base;

import io.github.alidee1410.core.init.ItemInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SoupItem;
import net.minecraft.world.World;

public class CannedFoodItem extends SoupItem {

	// Basically just like vanilla soup items but in a can instead of a bowl
	
	public CannedFoodItem(Properties builder) {
		super(builder);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
		return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(ItemInit.TIN_CAN.get());
	}
}
