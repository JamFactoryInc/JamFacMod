package net.minecraft.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class BaseFeatureConfig implements IFeatureConfig{
	public static final Codec<BigMushroomFeatureConfig> CODEC = RecordCodecBuilder.create((codec) -> {
	      return codec.group(BlockStateProvider.field_236796_a_.fieldOf("cap_provider").forGetter((privider) -> {
	         return privider.field_227272_a_;
	      }), BlockStateProvider.field_236796_a_.fieldOf("stem_provider").forGetter((provider) -> {
	         return provider.field_227273_b_;
	      }), Codec.INT.fieldOf("foliage_radius").orElse(2).forGetter((provider) -> {
	         return provider.field_227274_c_;
	      })).apply(codec, BigMushroomFeatureConfig::new);
	   });
	   public final BlockStateProvider field_227272_a_;
	   public final BlockStateProvider field_227273_b_;
	   public final int field_227274_c_;

	   public BaseFeatureConfig(BlockStateProvider p_i225832_1_, BlockStateProvider p_i225832_2_, int p_i225832_3_) {
	      this.field_227272_a_ = p_i225832_1_;
	      this.field_227273_b_ = p_i225832_2_;
	      this.field_227274_c_ = p_i225832_3_;
	   }
	}