package io.github.alidee1410.core.init;

import io.github.alidee1410.AlisBeanMod;
import io.github.alidee1410.common.items.CannedFoodItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	// Create item registry
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlisBeanMod.MOD_ID);
	
	// Items
	public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
			() -> new Item(new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB)));
	
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
			() -> new Item(new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB)));
	
	public static final RegistryObject<Item> TIN_CAN = ITEMS.register("tin_can",
			() -> new Item(new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB)));

	// Food
	public static final RegistryObject<CannedFoodItem> CAN_OF_BAKED_BEANS = ITEMS.register("can_of_baked_beans",
			() -> new CannedFoodItem(new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB).food(FoodInit.CAN_OF_BAKED_BEANS).maxStackSize(1)
					.containerItem(ItemInit.TIN_CAN.get())));
	
	// Crop Food
	public static final RegistryObject<Item> HARICOT_BEANS = ITEMS.register("haricot_beans",
			() -> new BlockNamedItem(BlockInit.HARICOT_BEAN_CROP.get(), new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB).food(FoodInit.HARICOT_BEANS)));
	
	// Ingredients
	public static final RegistryObject<Item> BOWL_OF_HARICOT_BEANS = ITEMS.register("bowl_of_haricot_beans",
			() -> new Item(new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB).maxStackSize(1).containerItem(Items.BOWL)));
	
	public static final RegistryObject<Item> BOILING_BOWL_OF_HARICOT_BEANS = ITEMS.register("boiling_bowl_of_haricot_beans",
			() -> new Item(new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB).maxStackSize(1).containerItem(Items.BOWL)));
	
	public static final RegistryObject<Item> BEAN_JUICE = ITEMS.register("bean_juice",
			() -> new Item(new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB).maxStackSize(1).containerItem(Items.BOWL)));
	
	// Block Items
	public static final RegistryObject<BlockItem> LAMP = ITEMS.register("lamp",
			() -> new BlockItem(BlockInit.LAMP.get(), new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB)));
	
	public static final RegistryObject<BlockItem> TIN_BLOCK = ITEMS.register("tin_block",
			() -> new BlockItem(BlockInit.TIN_BLOCK.get(), new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB)));
	
	public static final RegistryObject<BlockItem> STEEL_BLOCK = ITEMS.register("steel_block",
			() -> new BlockItem(BlockInit.STEEL_BLOCK.get(), new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB)));
	
	public static final RegistryObject<BlockItem> TIN_ORE = ITEMS.register("tin_ore",
			() -> new BlockItem(BlockInit.TIN_ORE.get(), new Item.Properties().group(AlisBeanMod.BEAN_MOD_TAB)));
}
