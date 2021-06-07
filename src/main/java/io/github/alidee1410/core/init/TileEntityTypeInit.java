package io.github.alidee1410.core.init;

import io.github.alidee1410.AlisBeanMod;
import io.github.alidee1410.common.blocks.machines.CanningMachineTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeInit {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, AlisBeanMod.MOD_ID);
	
	public static final RegistryObject<TileEntityType<CanningMachineTile>> CANNING_MACHINE = TILE_ENTITY_TYPES
			.register("canning_machine", () -> TileEntityType.Builder.create(CanningMachineTile::new, BlockInit.CANNING_MACHINE.get()).build(null));
}
