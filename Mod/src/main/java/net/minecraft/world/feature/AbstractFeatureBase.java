package net.minecraft.world.feature;

import java.util.Random;

import com.jam.jamfactory.util.RegistryHandler;
import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.util.Constants;

public class AbstractFeatureBase extends Feature<NoFeatureConfig>{

	public AbstractFeatureBase(Codec<NoFeatureConfig> codec) {
		super(codec);
	}


	@Override
	public boolean func_241855_a(ISeedReader world, ChunkGenerator p_241855_2_, Random p_241855_3_,
			BlockPos pos, NoFeatureConfig p_241855_5_) {
		world.setBlockState(pos, RegistryHandler.blocks.get("tungsten_ore").get().getDefaultState(), Constants.BlockFlags.DEFAULT);
		return true;
	}

}
