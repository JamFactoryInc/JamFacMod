package com.jam.jamfactory.block;

import com.jam.jamfactory.JamFactory;
import com.jam.jamfactory.util.RegistryHandler;
import com.jam.jamfactory.world.gen.OreGen;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BlockItemBase extends BlockItem{

	public BlockItemBase(Block block) {
		super(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS));

	}
	
	public BlockItemBase(Block block, JamFactory jam) {
		super(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS));
	}

}
