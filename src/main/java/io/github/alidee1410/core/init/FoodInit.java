package io.github.alidee1410.core.init;

import net.minecraft.item.Food;

public class FoodInit {
	
	// Foods
	public static final Food HARICOT_BEANS = new Food.Builder().hunger(1).saturation(0.1f).fastToEat().build();
	public static final Food BOWL_OF_BAKED_BEANS = new Food.Builder().hunger(6).saturation(0.6f).build();
	public static final Food CAN_OF_BAKED_BEANS = new Food.Builder().hunger(8).saturation(0.9f).build();
	public static final Food TOMATO = new Food.Builder().hunger(3).saturation(0.6f).build();
	public static final Food CORN = new Food.Builder().hunger(3).saturation(0.6f).build();
}
