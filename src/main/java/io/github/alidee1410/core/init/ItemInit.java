package io.github.alidee1410.core.init;

import io.github.alidee1410.AlisBeanMod;
import io.github.alidee1410.common.items.SpecialItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	// Create item registry
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlisBeanMod.MOD_ID);
	
	// Items
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
			() -> new Item(new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB)));
	
	public static final RegistryObject<SpecialItem> SPECIAL_ITEM = ITEMS.register("special_item",
			() -> new SpecialItem(new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB)));

	// Food
	public static final RegistryObject<Item> BAKED_BEANS_CAN = ITEMS.register("baked_beans_can",
			() -> new Item(new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB).food(FoodInit.BAKED_BEANS_CAN)));
	
	// Block Items
	public static final RegistryObject<BlockItem> STEEL_BLOCK = ITEMS.register("steel_block",
			() -> new BlockItem(BlockInit.STEEL_BLOCK.get(), new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB)));
	
	public static final RegistryObject<BlockItem> LAMP = ITEMS.register("lamp",
			() -> new BlockItem(BlockInit.LAMP.get(), new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB)));
}
