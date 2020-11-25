package com.jam.jamfactory.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

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
}
