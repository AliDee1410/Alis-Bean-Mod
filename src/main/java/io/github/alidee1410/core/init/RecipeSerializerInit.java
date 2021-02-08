package io.github.alidee1410.core.init;

import io.github.alidee1410.AlisBeanMod;
import io.github.alidee1410.common.recipes.CustomFurnaceRecipe;
import io.github.alidee1410.common.recipes.serializers.CustomFurnaceRecipeSerializer;
import io.github.alidee1410.core.interfaces.ICustomFurnaceRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializerInit {
	
	public static final IRecipeSerializer<CustomFurnaceRecipe> CUSTOM_FURNACE_RECIPE_SERIALIZER = new CustomFurnaceRecipeSerializer();
	public static final IRecipeType<ICustomFurnaceRecipe> CUSTOM_FURNACE_RECIPE_TYPE = registerType(ICustomFurnaceRecipe.RECIPE_TYPE_ID);
	
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(
			ForgeRegistries.RECIPE_SERIALIZERS, AlisBeanMod.MOD_ID);
	
	public static final RegistryObject<IRecipeSerializer<?>> CUSTOM_FURNACE_SERIALIZER = RECIPE_SERIALIZERS.register(
			"custom_furnace", () -> CUSTOM_FURNACE_RECIPE_SERIALIZER);
	
	private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
		
		@Override
		public String toString() {
			return Registry.RECIPE_TYPE.getKey(this).toString();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends IRecipeType<?>> T registerType(ResourceLocation recipeTypeId) {
		return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
	}
}
