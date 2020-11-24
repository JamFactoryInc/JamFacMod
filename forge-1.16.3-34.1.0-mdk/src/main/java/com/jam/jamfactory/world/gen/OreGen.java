package com.jam.jamfactory.world.gen;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collection;

import com.jam.jamfactory.JamFactory;
import com.jam.jamfactory.block.TungstenOre;
import com.jam.jamfactory.util.RegistryHandler;

/**
 * Ore generation
 * @author TechOFreak
 * Add OreGenerationExample.registerOres(); to your main class's setup method
 */

@Mod.EventBusSubscriber
public class OreGen {

	public OreGen()
	{
		
	}
	
    private static final ArrayList<ConfiguredFeature<?, ?>> overworldOres = new ArrayList<ConfiguredFeature<?, ?>>();
    private  static final ArrayList<ConfiguredFeature<?, ?>> netherOres = new ArrayList<ConfiguredFeature<?, ?>>();
    private  static final ArrayList<ConfiguredFeature<?, ?>> endOres = new ArrayList<ConfiguredFeature<?, ?>>();

    public  static  void registerOres(){
        //field_241882_a is for generating in stone, granite, diorite, and andesite
        //field_241883_b is for generating in netherrack
        //field_241884_c is for generating in netherrack, basalt and blackstone

        //Overworld Ore Register
        overworldOres.add(register("tungsten_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.field_241882_a, RegistryObject.of(new ResourceLocation("jam_factory:tungsten_ore"), ForgeRegistries.BLOCKS).get().getDefaultState(), 8)) //Vein Size
                .func_242733_d(15).func_242728_a() //Spawn height start
                .func_242731_b(100))); //Chunk spawn frequency
/*
        //Nether Ore Register
        netherOres.add(register("example_nether_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.field_241883_b, YOUR_ORE_BLOCK.getDefaultState(), 4)) //Vein Size
                .func_242733_d(64).func_242728_a() //Spawn height start
                .func_242731_b(64))); //Chunk spawn frequency

        //The End Ore Register
        endOres.add(register("example_end_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(
                new BlockMatchRuleTest(Blocks.END_STONE), YOUR_ORE_BLOCK.getDefaultState(), 4)) //Vein Size
                .func_242733_d(128).func_242728_a() //Spawn height start
                .func_242731_b(64))); //Chunk spawn frequency*/
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static  void gen(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        if(event.getCategory().equals(Biome.Category.NETHER)){
            for(ConfiguredFeature<?, ?> ore : netherOres){
                if (ore != null) generation.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        }
        if(event.getCategory().equals(Biome.Category.THEEND)){
            for(ConfiguredFeature<?, ?> ore : endOres){
                if (ore != null) generation.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        }
        for(ConfiguredFeature<?, ?> ore : overworldOres){
            if (ore != null) generation.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
        }
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.field_243653_e, JamFactory.MOD_ID + ":" + name, configuredFeature);
    }
    

}