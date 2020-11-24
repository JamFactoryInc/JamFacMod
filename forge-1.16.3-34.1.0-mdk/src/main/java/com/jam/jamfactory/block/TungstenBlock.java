package com.jam.jamfactory.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class TungstenBlock extends Block{

	public TungstenBlock() {
		super(Block.Properties.create(Material.ROCK)
				.hardnessAndResistance(3, 6)
				.sound(SoundType.STONE)
				.harvestLevel(2)
				.harvestTool(ToolType.PICKAXE)
				.func_235861_h_());

	}
	
	@Override
	public Block getBlock() {
		return super.getBlock();
	}
}
