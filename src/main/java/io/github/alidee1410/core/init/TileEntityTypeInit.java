package io.github.alidee1410.core.init;

import io.github.alidee1410.AlisBeanMod;
import io.github.alidee1410.common.tileentities.CustomFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeInit {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(
			ForgeRegistries.TILE_ENTITIES, AlisBeanMod.MOD_ID);
	
	/*
	public static final RegistryObject<TileEntityType<CustomFurnaceTileEntity>> MOD_CHEST_TE = TILE_ENTITY_TYPES
			.register("custom_furnace", () -> new TileEntityType.Builder.create(CustomFurnaceTileEntity::new, BlockInit.CUSTOM_FURNACE).build());
	*/
}
