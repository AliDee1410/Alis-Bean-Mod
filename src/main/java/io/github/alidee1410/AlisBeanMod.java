package io.github.alidee1410;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.alidee1410.core.init.BlockInit;
import io.github.alidee1410.core.init.ItemInit;
import io.github.alidee1410.core.init.RecipeSerializerInit;
import io.github.alidee1410.world.BeanModGeneration;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AlisBeanMod.MOD_ID)
public class AlisBeanMod
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "alis_bean_mod";
    public static final ItemGroup BEAN_MOD_TAB = new BeanModTab("bean_mod_tab");

    public AlisBeanMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        
        // Register the Item Register...
        ItemInit.ITEMS.register(bus);
        
        // Register the Block Register...
        BlockInit.BLOCKS.register(bus);
        
        RecipeSerializerInit.RECIPE_SERIALIZERS.register(bus);
        
        // World Generation
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, BeanModGeneration::generateOres);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, BeanModGeneration::generateBushPatches);
        
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    	
    }
    
    public static class BeanModTab extends ItemGroup {
    	
		public BeanModTab(String label) {
			super(label);
		}

		@Override
		public ItemStack createIcon() {
			return ItemInit.HARICOT_BEANS.get().getDefaultInstance();
		}
    }
}
