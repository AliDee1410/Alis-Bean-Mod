package io.github.alidee1410.common.recipes.serializers;

import com.google.gson.JsonObject;

import io.github.alidee1410.common.recipes.CustomFurnaceRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class CustomFurnaceRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CustomFurnaceRecipe>{

	@Override
	public CustomFurnaceRecipe read(ResourceLocation recipeId, JsonObject json) {
		ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
		Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));
		
		return new CustomFurnaceRecipe(recipeId, input, output);
	}

	@Override
	public CustomFurnaceRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		ItemStack output = buffer.readItemStack();
		Ingredient input = Ingredient.read(buffer);
		
		return new CustomFurnaceRecipe(recipeId, input, output);
	}

	@Override
	public void write(PacketBuffer buffer, CustomFurnaceRecipe recipe) {
		Ingredient input = recipe.getIngredients().get(0);
		input.write(buffer);
		
		buffer.writeItemStack(recipe.getRecipeOutput(), false);
	}
}
