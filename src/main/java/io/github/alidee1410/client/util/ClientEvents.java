package io.github.alidee1410.client.util;

import io.github.alidee1410.AlisBeanMod;
import io.github.alidee1410.common.blocks.machines.CoalPowerGeneratorScreen;
import io.github.alidee1410.core.init.BlockInit;
import io.github.alidee1410.core.init.ContainerTypeInit;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AlisBeanMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		// Connect container to gui
		ScreenManager.registerFactory(ContainerTypeInit.COAL_POWER_GENERATOR_CONTAINER.get(), CoalPowerGeneratorScreen::new);
		
		RenderTypeLookup.setRenderLayer(BlockInit.HARICOT_BEAN_CROP.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.WILD_HARICOT_BEAN_BUSH.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.WILD_HARICOT_BEAN_CROP.get(), RenderType.getCutout());
	}
}
