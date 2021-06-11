package io.github.alidee1410;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.alidee1410.core.init.BlockInit;
import io.github.alidee1410.core.init.ContainerTypeInit;
import io.github.alidee1410.core.init.ItemInit;
import io.github.alidee1410.core.init.TileEntityTypeInit;
import io.github.alidee1410.world.BeanModGeneration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AlisBeanMod.MOD_ID)
public class AlisBeanMod
{
    public static final String MOD_ID = "alis_bean_mod";
    
    public static final Logger LOGGER = LogManager.getLogger();
    public static final BeanModCreativeTab CREATIVE_TAB = new BeanModCreativeTab();

    public AlisBeanMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        
        // Registers
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        TileEntityTypeInit.TILE_ENTITY_TYPES.register(bus);
        ContainerTypeInit.CONTAINER_TYPES.register(bus);
        
        
        // World Generation
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, BeanModGeneration::generateOres);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, BeanModGeneration::generateBushPatches);
        
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    	
    }
}
