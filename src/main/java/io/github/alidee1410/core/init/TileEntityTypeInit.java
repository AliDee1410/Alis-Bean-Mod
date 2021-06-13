package io.github.alidee1410.core.init;

import io.github.alidee1410.AlisBeanMod;
import io.github.alidee1410.common.blocks.machines.CoalPowerGeneratorTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeInit {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, AlisBeanMod.MOD_ID);
	
	public static final RegistryObject<TileEntityType<CoalPowerGeneratorTile>> COAL_POWER_GENERATOR_TILE = TILE_ENTITY_TYPES
			.register("coal_power_generator", () -> TileEntityType.Builder.create(CoalPowerGeneratorTile::new, BlockInit.COAL_POWER_GENERATOR.get()).build(null));
}
