package io.github.alidee1410.core.init;

import io.github.alidee1410.AlisBeanMod;
import io.github.alidee1410.common.blocks.machines.CoalPowerGeneratorContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeInit {

	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, AlisBeanMod.MOD_ID);
	
	public static final RegistryObject<ContainerType<CoalPowerGeneratorContainer>> COAL_POWER_GENERATOR_CONTAINER = CONTAINER_TYPES
			.register("coal_power_generator", () -> IForgeContainerType.create(CoalPowerGeneratorContainer::new));
}
