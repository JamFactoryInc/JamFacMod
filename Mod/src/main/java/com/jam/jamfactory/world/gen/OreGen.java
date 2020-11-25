package com.jam.jamfactory.world.gen;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.HashMap;

import com.jam.jamfactory.JamFactory;
import com.jam.jamfactory.util.RegistryHandler;



@Mod.EventBusSubscriber
public class OreGen 
{
	private static final RuleTest OVERWORLD = OreFeatureConfig.FillerBlockType.field_241882_a;
    private static final RuleTest NETHER = OreFeatureConfig.FillerBlockType.field_241883_b;
    private static final RuleTest END = OreFeatureConfig.FillerBlockType.field_241884_c;
    
	public  static  void registerOres()
    {
    	addOre("tungsten_ore", 40, 20, 8, OVERWORLD);

    }
	
    private static final HashMap<String, ConfiguredFeature<?, ?>> overworldOres = new HashMap<String, ConfiguredFeature<?, ?>>();
    private static final HashMap<String, ConfiguredFeature<?, ?>> netherOres = new HashMap<String, ConfiguredFeature<?, ?>>();
    private static final HashMap<String, ConfiguredFeature<?, ?>> endOres = new HashMap<String, ConfiguredFeature<?, ?>>();
    
    private static final HashMap<Biome.Category, HashMap<String, ConfiguredFeature<?, ?>>> biomeOres = new HashMap<>();
    static 
    {
    	biomeOres.put(Biome.Category.NONE, overworldOres);
    	biomeOres.put(Biome.Category.NETHER, netherOres);
    	biomeOres.put(Biome.Category.THEEND, endOres);
    }
    
    

    private static void addOre(String id, int maxSpawnHeight, int attemptsPerChunk, int veinSize, RuleTest fillerBlockType) 
    {
    	overworldOres.put(id,register(id, Feature.ORE.withConfiguration(new OreFeatureConfig(
                fillerBlockType, RegistryHandler.blocks.get(id).get().getDefaultState(), veinSize)) //Vein Size
                .func_242733_d(maxSpawnHeight).func_242728_a() //Spawn height start
                .func_242731_b(attemptsPerChunk)));
	}

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static  void gen(BiomeLoadingEvent event) 
    {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        
        biomeOres.getOrDefault(event.getCategory(), overworldOres).values().forEach(ore -> {if (ore != null) generation.func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, ore);});
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) 
    {
        return Registry.register(WorldGenRegistries.field_243653_e, JamFactory.MOD_ID + ":" + name, configuredFeature);
    }
    

}