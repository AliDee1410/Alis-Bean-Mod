package io.github.alidee1410;

import io.github.alidee1410.core.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class BeanModCreativeTab extends ItemGroup {

	public BeanModCreativeTab() {
		super("bean_mod_creative_tab");
	}

	@Override
	public ItemStack createIcon() {
		return ItemInit.HARICOT_BEANS.get().getDefaultInstance();
	}
}
