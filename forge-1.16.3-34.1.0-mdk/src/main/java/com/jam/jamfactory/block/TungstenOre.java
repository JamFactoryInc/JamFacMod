package com.jam.jamfactory.block;

import java.util.ArrayList;
import java.util.List;

import com.jam.jamfactory.util.RegistryHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.SilkTouchEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext.Builder;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.ForgeRegistries;

public class TungstenOre extends Block {

	public TungstenOre() {
		super(Block.Properties.create(Material.ROCK)
				.hardnessAndResistance(3, 6)
				.sound(SoundType.STONE)
				.harvestLevel(2)
				.harvestTool(ToolType.PICKAXE)
				.func_235861_h_());

	}
	
	@Override
	public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
		// TODO Auto-generated method stub
		return silktouch>0?0:fortune+1;
	}
	
	/*
	@Override
	public List<ItemStack> getDrops(BlockState state, Builder builder) {
		
		ArrayList<ItemStack> drop = new ArrayList<ItemStack>();
		drop.add(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("jam_factory:tungsten_crystal")), 1));
		return drop;
	}*/
	


}
